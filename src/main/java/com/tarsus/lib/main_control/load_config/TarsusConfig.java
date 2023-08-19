package com.tarsus.lib.main_control.load_config;

//public class TarsusConfig {
//    public static class Server {
//        public String project;
//        public Database database;
//    }
//
//    public static class Database {
//        public String url;
//        public String username;
//        public String password;
//        public int initSize;
//        public int minIdle;
//        public int maxActive;
//        public int maxAwait;
//        public int timeBetweenConnectErrorMillis;
//    }
//
//
//    public Server server;
//}

import java.util.Map;

public class TarsusConfig {
    public static class Server {
        public String project;
        public Map<String, Object> database;
        public Map<String,String> aliases;
    }

    public Server server;
}

