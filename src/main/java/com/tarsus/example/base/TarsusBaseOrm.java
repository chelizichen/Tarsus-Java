package com.tarsus.example.base;


import com.tarsus.example.decorator.orm.Column;
import com.tarsus.example.decorator.orm.Entity;
import com.tarsus.example.decorator.orm.Key;
import com.tarsus.example.decorator.orm.Keyword;
import com.tarsus.example.utils.SqlUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author leemulus
 * @since 2023.2.17
 * ORM父类 所有 Entity 实体类基于此父类
 */
public class TarsusBaseOrm {

    protected static HashMap<String, HashMap<String, String>> DBFieldsMap = new HashMap<>();

    public String EntityName;
    public String KeyName;
    public String KeywordName;

    public TarsusBaseOrm() {
        this.EntityName = this.getClass().getSimpleName();
        final Field[] declaredFields = this.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            final boolean isKey = declaredField.isAnnotationPresent(Key.class);
            final boolean isKeyword = declaredField.isAnnotationPresent(Keyword.class);
            // 找到 实体类的Key
            if (isKey) {
                String value = declaredField.getAnnotation(Column.class).value();
                if(value.equals("")){
                    this.KeyName = declaredField.getName();
                }else {
                    this.KeyName = value;
                }
            }
            // 找到 实体类的Value
            if (isKeyword) {
                String value = declaredField.getAnnotation(Column.class).value();
                if(value.equals("")){
                    this.KeywordName = declaredField.getName();
                }else {
                    this.KeywordName = value;
                }
            }
        }
    }

    /**
     * 从数据库中拿到单个数据
     * 需要在定义数据库实体类时使用 @Key 注解代表是唯一id
     *
     * @param val
     * @return null || Entity extends ArcBaseOrm
     */
    public <T extends TarsusBaseOrm> T getOneBy(String val) {
        final String[] strings = new String[]{val};
        final List<? extends TarsusBaseOrm> query = TarsusBaseOrm.query(
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

    public  <T extends TarsusBaseOrm>List<T> getList(String page, String size) {
        String paginationSql = SqlUtil.getPagination(page,size);
        final List<? extends TarsusBaseOrm> query = TarsusBaseOrm.query(
                "select * from " + this.EntityName + paginationSql,
                this.getClass()
        );
        return (List<T>) query;
    }

    public Integer getCountBy(String keyword){
        int total= 0;
        String newkeyword = SqlUtil.getMatchKeyword(keyword);
        String[] args = new String[]{newkeyword};
        ResultSet query = TarsusBaseOrm.query(
                "select COUNT(*) as total from " + this.EntityName
                        + " where " + this.KeywordName
                        + " like  ? ",
                args
        );
        try {
            while (query.next()){
                total = Integer.parseInt(query.getString("total"));
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return total;
    }


    public <T extends TarsusBaseOrm> List<T> getList(String keyword, String page, String size) {

        String paginationSql = SqlUtil.getPagination(page,size);
        // 默认全匹配
        keyword = "%" + keyword + "%";
        final String[] strings = new String[]{keyword};
        final List<? extends TarsusBaseOrm> query = TarsusBaseOrm.query(
                "select * from " + this.EntityName +
                        " where " + this.KeywordName +
                        " like ? " + paginationSql,
                strings,
                this.getClass()
        );
        return (List<T>) query;
    }


    public static<T> ResultSet query(String sql){
        Connection connect = null;
        ResultSet resultSet = null;

        try {
            connect = TarsusDataBase.getConnect();
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static<T> ResultSet query(String sql,String[] args){
        ResultSet resultSet = null;
        try {
            Connection connect = TarsusDataBase.getConnect();
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setString(i+1,args[i]);
            }
            System.out.println(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static <T> List<T> query(String sql, Class<T> EntityParams) {
        try {
            Connection connect = TarsusDataBase.getConnect();
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            return getData(preparedStatement, EntityParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> query(String sql, String[] args, Class<T> EntityParams) {
        try {
            Connection connect = TarsusDataBase.getConnect();
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            System.out.println(preparedStatement);
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
        final boolean hasDbFieldsMap = TarsusBaseOrm.DBFieldsMap.containsKey(EntityParams.getSimpleName());
        if (hasDbFieldsMap) {
            ArcFieldMap = TarsusBaseOrm.DBFieldsMap.get(EntityParams.getSimpleName());
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
            T OrmMapperClass = null;
            try {
                // 创建新的实例
                OrmMapperClass = EntityParams.getConstructor().newInstance();
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
                    declaredField.set(OrmMapperClass, val);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            T_List.add(OrmMapperClass);
        }
        return T_List;
    }
}
