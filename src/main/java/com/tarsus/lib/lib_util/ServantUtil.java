package com.tarsus.lib.lib_util;

import com.tarsus.lib.main_control.load_config.Parse2Servant;

import java.util.HashMap;
import java.util.Map;

public class ServantUtil {
    static class Param {
        public String key;
        public String param;

        public Param(String key, String param) {
            this.key = key;
            this.param = param;
        }
    }

    static Param[] params = {
            new Param("-l", "language"),
            new Param("-t", "proto"),
            new Param("-h", "host"),
            new Param("-p", "port"),
            new Param("-w", "weight"),
    };

    public static Parse2Servant parse(String servant) {
        Parse2Servant obj = new Parse2Servant();
        Map<String, String> paramMap = new HashMap<>();

        for (Param param : params) {
            int index = servant.indexOf(param.key);
            int end = servant.indexOf(" ", index + 3);
            if (end == -1) {
                end = servant.length();
            }
            String substr = servant.substring(index + 2, end).trim();
            paramMap.put(param.param, substr);

            if (substr.endsWith("ms") && param.key.equals("-t")) {
                paramMap.put(param.param, "ms");
            }
            if (substr.endsWith("http") && param.key.equals("-t")) {
                paramMap.put(param.param, "http");
            }
        }

        int servantEnd = servant.indexOf(" ");
        String servantName = servant.substring(0, servantEnd).trim();
        paramMap.put("serverName", servantName);
        paramMap.put("serverGroup", servantName.substring(1, servantName.indexOf("/")));
        paramMap.put("serverProject", servantName.substring(servantName.indexOf("/") + 1));

        obj.language = paramMap.get("language");
        obj.proto = paramMap.get("proto");
        obj.host = paramMap.get("host");
        obj.port = Integer.valueOf(paramMap.get("port"));
        obj.serverName = paramMap.get("serverName");
        obj.serverGroup = paramMap.get("serverGroup");
        obj.serverProject = paramMap.get("serverProject");
        obj.weight = paramMap.get("weight");

        return obj;
    }
}
