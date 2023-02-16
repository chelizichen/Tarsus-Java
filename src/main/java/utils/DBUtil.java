package utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtil {
    private DruidDataSource dataSource = null;

    private void initDataSource() throws Exception{
        if(dataSource == null){
            dataSource = new DruidDataSource();
            dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/lmr_medical?useSSL=false&useUnicode=true&characterEncoding=UTF-8");
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUsername("root");
            dataSource.setPassword("123456");
            dataSource.setInitialSize(1);
            dataSource.setMinIdle(1);
            dataSource.setMaxActive(10);
            dataSource.setMaxWait(20000);
            dataSource.setTimeBetweenConnectErrorMillis(2000);
            dataSource.setValidationQuery("select 'x'");
            dataSource.setTestWhileIdle(true);
            dataSource.setTestOnBorrow(true);
        }
    }
    public Connection getConnect() throws Exception {
        Connection conn = null;
        initDataSource();
        conn = dataSource.getConnection();
        return conn;
    }
}

class TestClass{
    public static void main(String[] args) {
        final DBUtil dbUtil = new DBUtil();
        try {
            final Connection connect = dbUtil.getConnect();
            final ResultSet resultSet = connect.prepareStatement("select * from drug").executeQuery();
            System.out.println(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
