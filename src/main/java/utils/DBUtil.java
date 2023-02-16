package utils;

import com.alibaba.druid.pool.DruidDataSource;
import decorator.orm.ArcEntity;
import decorator.orm.ArcField;
import enity.Drug;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DBUtil {
    private static DruidDataSource dataSource = null;

    private static void initDataSource() {
        if(DBUtil.dataSource == null){
            DBUtil.dataSource = new DruidDataSource();
            DBUtil.dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/lmr_medical?useSSL=false&serverTimezone=UTC");
            DBUtil.dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            DBUtil.dataSource.setUsername("root");
            DBUtil.dataSource.setPassword("123456");
            DBUtil.dataSource.setInitialSize(1);
            DBUtil.dataSource.setMinIdle(1);
            DBUtil.dataSource.setMaxActive(10);
            DBUtil.dataSource.setMaxWait(20000);
            DBUtil.dataSource.setTimeBetweenConnectErrorMillis(2000);
            DBUtil.dataSource.setValidationQuery("select 'x'");
        }
    }
    public static Connection getConnect() throws Exception {
        Connection conn = null;
        initDataSource();
        conn = DBUtil.dataSource.getConnection();
        return conn;
    }

    public static <T> List<T> query(String sql,Class<T> EntityParams){
        try {
            Connection connect = DBUtil.getConnect();
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            boolean isEntity = EntityParams.isAnnotationPresent(ArcEntity.class);
            HashSet<String> ArcFieldSet = new HashSet<>();
            List<T> T_List = new ArrayList<T>();
            Field[] declaredFields = EntityParams.getDeclaredFields();

            if(isEntity){
                // 将实体类的映射为数据库表结构
                for (Field field : declaredFields) {
                    boolean isArcField = field.isAnnotationPresent(ArcField.class);
                    if(isArcField){
                        String value = field.getAnnotation(ArcField.class).value();
                        // 如果为默认值
                        if(value.equals("")){
                            value = field.getName();
                        }
                        ArcFieldSet.add(value);
                    }
                }
            }
            while (resultSet.next()){
                T t = EntityParams.newInstance();
                for(String fieldStr:ArcFieldSet){
                    System.out.println("fieldStr"+fieldStr);
                    String val = resultSet.getString(fieldStr);
                    EntityParams.getDeclaredField(fieldStr).setAccessible(true);
                    EntityParams.getDeclaredField(fieldStr).set(t,val);
                }

                T_List.add(t);
            }
            return T_List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> query(String sql,String[] args,Class<T> EntityParams){
        try {
            Connection connect = DBUtil.getConnect();
            PreparedStatement preparedStatement = connect.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setString(i+1,args[i]);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isEntity = EntityParams.isAnnotationPresent(ArcEntity.class);
            HashSet<String> ArcFieldSet = new HashSet<>();
            List<T> T_List = new ArrayList<T>();
            Field[] declaredFields = EntityParams.getDeclaredFields();

            if(isEntity){
                // 将实体类的映射为数据库表结构
                for (Field field : declaredFields) {
                    boolean isArcField = field.isAnnotationPresent(ArcField.class);
                    if(isArcField){
                        String value = field.getAnnotation(ArcField.class).value();
                        // 如果为默认值
                        if(value.equals("")){
                            value = field.getName();
                        }
                        ArcFieldSet.add(value);
                    }
                }
            }
            while (resultSet.next()){
                T t = EntityParams.getConstructor().newInstance();
                for(String fieldStr:ArcFieldSet){
                    String val = resultSet.getString(fieldStr);
                    EntityParams.getDeclaredField(fieldStr).setAccessible(true);
                    EntityParams.getDeclaredField(fieldStr).set(t,val);
                }

                T_List.add(t);
            }
            return T_List;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

class TestClass{

    public static void main(String[] args) {
        List<Drug> query = DBUtil.query("select * from drug", Drug.class);
        System.out.println(query.size());
        System.out.println(query);
    }
}
