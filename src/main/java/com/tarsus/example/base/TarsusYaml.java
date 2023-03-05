package com.tarsus.example.base;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TarsusYaml {
    // 开启的TCP端口号
    public Integer port;

    // 异步事件目录
    public static String events_path;

    // 注册事件目录
    public static String register_path;

    public Map<String, Object> server = new HashMap<>();



    public static String pkg_name;
    public TarsusYaml(){
        Yaml yaml = new Yaml();
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("tarsus.config.yaml");
        Map<String,Object> load = yaml.load(resourceAsStream);
        this.server = (Map<String, Object>) load.get("server");
        System.out.println(load);
        Object port = this.server.get("port");

        if(port == null){
            this.port = 8080;
        }else {
            this.port = (Integer) port;
        }
    }

    public void read_config(){
        Map<String,String> aliases = (Map<String, String>) this.server.get("aliases");
        String publicPath = aliases.get("publicPath");

        TarsusYaml.events_path = pkg_name + "." + aliases.get("events");
        TarsusYaml.register_path = pkg_name + "." + aliases.get("register");
        String events = publicPath + "."+ TarsusYaml.events_path;
        String register = publicPath + "." +TarsusYaml.register_path;


        try {
            this.read_path(events,events_path);
            this.read_path(register,register_path);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }
    // 读取接口
    public void read_path(String src_path,String pkg_path) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if(src_path == null){
            throw new Error("com.tarsus.example.register path must be defined");
        }
        Set<Class<?>> classes = null;
        try {
            classes = getClasses(src_path,pkg_path);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Class<?> aClass : classes) {
            aClass.getConstructor().newInstance();
            System.out.println(aClass.getName()+" is loaded");
        }
    }

    public static Set<Class<?>> getClasses(String packageName,String pkg_path) throws ClassNotFoundException {
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
                Set<Class<?>> subClasses = getClasses(packageName + "." + file.getName(),pkg_path);
                classes.addAll(subClasses);
            } else {
                String className = file.getName().substring(0,file.getName().lastIndexOf("."));
                String reflect_var = pkg_path + "." + className;
                Class<?> clazz = Class.forName(reflect_var);
                classes.add(clazz);
            }
        }

        return classes;
    }

}
