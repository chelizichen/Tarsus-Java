package com.tarsus.lib.main_control.load_config;

import com.tarsus.lib.main_control.load_server.impl.TarsusStream;
import com.tarsus.lib.lib_util.ServantUtil;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class LoadConfig {
    // 开启的TCP端口号
    public Integer port;
    public TarsusConfig tarsusConfig;
    public static String publicPath;
    public static String struct;
    public static String packageName;

    public LoadConfig() {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("tarsus.config.yaml");
        Yaml yaml = new Yaml();
        TarsusConfig tarsusConfig = yaml.loadAs(resourceAsStream, TarsusConfig.class);
        Parse2Servant parse = ServantUtil.parse(tarsusConfig.server.project);

        this.port = parse.port;
        this.tarsusConfig = tarsusConfig;

        LoadConfig.publicPath = tarsusConfig.server.aliases.get("publicPath");
        LoadConfig.struct = tarsusConfig.server.aliases.get("struct");
    }


    public void LoadStruct() {
        String struct_dir_path = publicPath + "." + packageName + "." + struct;
        final Set<Class<?>> struct_classes = read_path(struct_dir_path, struct);
        SetToLoad(struct_classes);
    }

    public static Set<Class<?>> read_path(String src_path, String pkg_path) {
//        if (src_path == null) {
//            throw new Error("com.tarsus.example. path must be defined");
//        }
        Set<Class<?>> classes = null;
        try {
            classes = getClasses(src_path, pkg_path);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classes;
    }

    public static Set<Class<?>> getClasses(String packageName, String pkg_path) throws ClassNotFoundException {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        String packageNamePath = packageName.replace(".", "/");

        File packageDir = new File(packageNamePath);
        if (!packageDir.exists()) {
            return classes;
        }

        File[] files = packageDir.listFiles();
        if (files == null) {
            System.out.println("没有加载的类");
            return classes;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                Set<Class<?>> subClasses = getClasses(packageName + "." + file.getName(), pkg_path);
                classes.addAll(subClasses);
            } else {
                String target$className = Paths.get(LoadConfig.packageName,pkg_path,file.getName()).toString().replace("/",".").replace(".java","");
                Class<?> clazz = Class.forName(target$className);
                classes.add(clazz);
            }
        }

        return classes;
    }

    public static void SetToLoad(Set<Class<?>> classes) {
        for (Class<?> clazz : classes) {
            TarsusStream.SetClass(clazz);
            System.out.println(clazz.getName() + " is loaded");
        }
    }

}