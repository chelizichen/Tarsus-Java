package base;

import decorator.orm.Entity;
import decorator.orm.Column;
import decorator.orm.Key;
import decorator.orm.Keyword;
import utils.DBUtil;
import utils.SqlUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author leemulus
 * @since 2023.2.17
 * ORM父类 所有 Entity 实体类基于此父类
 */
public class AdoBaseOrm {

    protected static HashMap<String, HashMap<String, String>> DBFieldsMap = new HashMap<>();

    public String EntityName;
    public String KeyName;
    public String KeywordName;

    public AdoBaseOrm() {
        this.EntityName = this.getClass().getSimpleName();
        final Field[] declaredFields = this.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            final boolean isKey = declaredField.isAnnotationPresent(Key.class);
            final boolean isKeyword = declaredField.isAnnotationPresent(Keyword.class);
            // 找到 实体类的Key
            if (isKey) {
                this.KeyName = declaredField.getName();
            }

            // 找到 实体类的Value
            if (isKeyword) {
                this.KeywordName = declaredField.getName();
            }
        }
    }

    /**
     * 从数据库中拿到单个数据
     * 需要在定义数据库实体类时使用 @Key 注解代表是唯一id
     *
     * @param val
     * @return null || Entity extends AdoBaseOrm
     */
    public <T extends AdoBaseOrm> T getOneBy(String val) {
        final String[] strings = new String[]{val};
        final List<? extends AdoBaseOrm> query = AdoBaseOrm.query(
                "select * from " + this.EntityName + " where " + this.KeyName + " = ? ",
                strings,
                this.getClass()
        );
        if (query.size() == 1) {
            return (T) query.get(0);
        } else {
            return null;
        }
    }

    public  <T extends AdoBaseOrm>List<T> getList(String page, String size) {
        String paginationSql = SqlUtil.getPagination(page,size);
        final List<? extends AdoBaseOrm> query = AdoBaseOrm.query(
                "select * from " + this.EntityName + paginationSql,
                this.getClass()
        );
        return (List<T>) query;
    }


    public <T extends AdoBaseOrm> List<T> getList(String keyword, String page, String size) {

        String paginationSql = SqlUtil.getPagination(page,size);

        // 默认全匹配
        keyword = "%" + keyword + "%";
        final String[] strings = new String[]{keyword,};
        final List<? extends AdoBaseOrm> query = AdoBaseOrm.query(
                "select * from " + this.EntityName +
                        " where " + this.KeywordName +
                        " like ? " + paginationSql,
                strings,
                this.getClass()
        );
        return (List<T>) query;
    }

    public static <T> List<T> query(String sql, Class<T> EntityParams) {
        try {
            Connection connect = DBUtil.getConnect();
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            return getData(preparedStatement, EntityParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> query(String sql, String[] args, Class<T> EntityParams) {
        try {
            Connection connect = DBUtil.getConnect();
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setString(i + 1, args[i]);
            }
            return getData(preparedStatement, EntityParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param preparedStatement
     * @param EntityParams
     * @param <T>
     * @return
     */
    private static <T> List<T> getData(PreparedStatement preparedStatement, Class<T> EntityParams) {

        List<T> T_List = new ArrayList<T>();

        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 处理层
        // 实体类的字段
        Field[] declaredFields = EntityParams.getDeclaredFields();

        // 实体 - 数据库 映射Map
        HashMap<String, String> ArcFieldMap = new HashMap<>();
        final boolean hasDbFieldsMap = AdoBaseOrm.DBFieldsMap.containsKey(EntityParams.getSimpleName());
        if (hasDbFieldsMap) {
            ArcFieldMap = AdoBaseOrm.DBFieldsMap.get(EntityParams.getSimpleName());
        } else {
            boolean isEntity = EntityParams.isAnnotationPresent(Entity.class);
            if (isEntity) {
                // 将实体类的映射为数据库表结构
                for (Field field : declaredFields) {
                    boolean isArcField = field.isAnnotationPresent(Column.class);
                    if (isArcField) {
                        String value = field.getAnnotation(Column.class).value();
                        // 如果为默认值
                        if (value.equals("")) {
                            value = field.getName();
                        }
                        ArcFieldMap.put(field.getName(), value);
                    }
                }
            } else {
                return null;
            }
        }

        while (true) {
            try {
                if (!Objects.requireNonNull(resultSet).next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            T t = null;
            try {
                // 创建新的实例
                t = EntityParams.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }

            for (Field fieldStr : declaredFields) {
                final String db_filed = ArcFieldMap.get(fieldStr.getName());
                String val = null;
                try {
                    val = resultSet.getString(db_filed);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                final Field declaredField;
                try {
                    declaredField = EntityParams.getDeclaredField(fieldStr.getName());
                    declaredField.setAccessible(true);
                    declaredField.set(t, val);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            T_List.add(t);
        }
        return T_List;
    }
}
