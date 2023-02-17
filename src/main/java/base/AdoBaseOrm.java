package base;

import decorator.orm.AdoEntity;
import decorator.orm.AdoField;
import decorator.orm.Key;
import utils.DBUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AdoBaseOrm {

    public String EntityName;
    public String KeyName;

    public AdoBaseOrm() {
        this.EntityName = this.getClass().getSimpleName();
        final Field[] declaredFields = this.getClass().getDeclaredFields();
        // 找到 实体类的Key
        for (Field declaredField : declaredFields) {
            final boolean isKey = declaredField.isAnnotationPresent(Key.class);
            if (isKey) {
                this.KeyName = declaredField.getName();
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

    public static <T> List<T> query(String sql, Class<T> EntityParams) {
        List<T> T_List = new ArrayList<T>();

        try {
            Connection connect = DBUtil.getConnect();
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            boolean isEntity = EntityParams.isAnnotationPresent(AdoEntity.class);

            // 实体类的字段
            Field[] declaredFields = EntityParams.getDeclaredFields();

            // 实体 - 数据库 映射Map
            final HashMap<String, String> ArcFieldMap = new HashMap<>();


            if (isEntity) {
                // 将实体类的映射为数据库表结构
                for (Field field : declaredFields) {
                    boolean isArcField = field.isAnnotationPresent(AdoField.class);
                    if (isArcField) {
                        String value = field.getAnnotation(AdoField.class).value();
                        // 如果为默认值
                        if (value.equals("")) {
                            value = field.getName();
                        }
                        ArcFieldMap.put(field.getName(), value);
                    }
                }
            }
            while (resultSet.next()) {
                T t = EntityParams.getConstructor().newInstance();

                for (Field fieldStr : declaredFields) {
                    final String db_filed = ArcFieldMap.get(fieldStr.getName());
                    String val = resultSet.getString(db_filed);
                    final Field declaredField = EntityParams.getDeclaredField(fieldStr.getName());
                    declaredField.setAccessible(true);
                    declaredField.set(t, val);
                }
                T_List.add(t);
            }
            return T_List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return T_List;
    }

    public static <T> List<T> query(String sql, String[] args, Class<T> EntityParams) {
        List<T> T_List = new ArrayList<T>();
        try {
            Connection connect = DBUtil.getConnect();
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setString(i + 1, args[i]);
            }

            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isEntity = EntityParams.isAnnotationPresent(AdoEntity.class);

            // 处理层
            // 实体类的字段
            Field[] declaredFields = EntityParams.getDeclaredFields();

            // 实体 - 数据库 映射Map
            final HashMap<String, String> ArcFieldMap = new HashMap<>();


            if (isEntity) {
                // 将实体类的映射为数据库表结构
                for (Field field : declaredFields) {
                    boolean isArcField = field.isAnnotationPresent(AdoField.class);
                    if (isArcField) {
                        String value = field.getAnnotation(AdoField.class).value();
                        // 如果为默认值
                        if (value.equals("")) {
                            value = field.getName();
                        }
                        ArcFieldMap.put(field.getName(), value);
                    }
                }
            }
            while (resultSet.next()) {
                T t = EntityParams.getConstructor().newInstance();

                for (Field fieldStr : declaredFields) {
                    final String db_filed = ArcFieldMap.get(fieldStr.getName());
                    String val = resultSet.getString(db_filed);
                    final Field declaredField = EntityParams.getDeclaredField(fieldStr.getName());
                    declaredField.setAccessible(true);
                    declaredField.set(t, val);
                }
                T_List.add(t);
            }
            return T_List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return T_List;
    }

}
