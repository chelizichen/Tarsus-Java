package com.tarsus.lib.main_control.load_config;
import com.tarsus.lib.util.ServantUtil;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

public class LoadConfig {
    // 开启的TCP端口号
    public Integer port;
    public TarsusConfig tarsusConfig;
    public LoadConfig() {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("tarsus.config.yaml");
        Yaml yaml = new Yaml();
        TarsusConfig tarsusConfig = yaml.loadAs(resourceAsStream, TarsusConfig.class);
        Parse2Servant parse = ServantUtil.parse(tarsusConfig.server.project);

        this.port = parse.port;
        this.tarsusConfig = tarsusConfig;
    }
    public static String[] proto = new String[]{"[#1]",
            "[#2]", "[#3]", "[#4]", "[#5]",
            "[#6]", "[#7]", "[#8]", "[#9]",
            "[##]"
    };
}