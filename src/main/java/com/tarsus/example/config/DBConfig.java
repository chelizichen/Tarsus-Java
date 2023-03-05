package com.tarsus.example.config;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;

public class DBConfig {
    private static DruidDataSource dataSource = null;

    private static void initDataSource() {
        if(DBConfig.dataSource == null){
            DBConfig.dataSource = new DruidDataSource();
            DBConfig.dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/lmr_medical?useSSL=false&serverTimezone=UTC");
            DBConfig.dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            DBConfig.dataSource.setUsername("root");
            DBConfig.dataSource.setPassword("123456");
            DBConfig.dataSource.setInitialSize(1);
            DBConfig.dataSource.setMinIdle(1);
            DBConfig.dataSource.setMaxActive(10);
            DBConfig.dataSource.setMaxWait(20000);
            DBConfig.dataSource.setTimeBetweenConnectErrorMillis(2000);
            DBConfig.dataSource.setValidationQuery("select 'x'");
        }
    }
    public static Connection getConnect() throws Exception {
        Connection conn = null;
        initDataSource();
        conn = DBConfig.dataSource.getConnection();
        return conn;
    }


}

