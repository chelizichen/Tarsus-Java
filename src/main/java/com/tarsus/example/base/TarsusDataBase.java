package com.tarsus.example.base;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.Connection;

public class TarsusDataBase {
    private static DruidDataSource dataSource = null;

    private static void initDataSource() {
        if(TarsusDataBase.dataSource == null){
            TarsusDataBase.dataSource = new DruidDataSource();
            TarsusDataBase.dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/lmr_medical?useSSL=false&serverTimezone=UTC");
            TarsusDataBase.dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            TarsusDataBase.dataSource.setUsername("root");
            TarsusDataBase.dataSource.setPassword("123456");
            TarsusDataBase.dataSource.setInitialSize(1);
            TarsusDataBase.dataSource.setMinIdle(1);
            TarsusDataBase.dataSource.setMaxActive(10);
            TarsusDataBase.dataSource.setMaxWait(20000);
            TarsusDataBase.dataSource.setTimeBetweenConnectErrorMillis(2000);
            TarsusDataBase.dataSource.setValidationQuery("select 'x'");
        }
    }
    public static Connection getConnect() throws Exception {
        Connection conn = null;
        initDataSource();
        conn = TarsusDataBase.dataSource.getConnection();
        return conn;
    }


}

