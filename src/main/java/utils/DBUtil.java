package utils;

import base.ArcBaseOrm;
import com.alibaba.druid.pool.DruidDataSource;
import enity.Drug;

import java.sql.Connection;

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


}

