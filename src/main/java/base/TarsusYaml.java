package base;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TarsusYaml {
    // 开启的TCP端口号
    // interFace 路径
    public Integer port;

    public TarsusYaml(){
        Yaml yaml = new Yaml();
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("tarsus.config.yaml");
        Map<String,Object> load = yaml.load(resourceAsStream);
        Map<String,Object> server = (Map<String, Object>) load.get("server");
        System.out.println(load);
        Object port = server.get("port");

        if(port == null){
            this.port = 8080;
        }else {
            this.port = (Integer) port;
        }

        String register = (String) server.get("register");
        String events = (String) server.get("events");

        try {
            this.read_register(register);
            this.read_register(events);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }
    // 读取接口
    public void read_register(String src_path) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if(src_path == null){
            throw new Error("register path must be defined");
        }
        Set<Class<?>> classes = null;
        try {
            classes = getClasses(src_path);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Class<?> aClass : classes) {
            aClass.getConstructor().newInstance();
            System.out.println(aClass.getName()+" is loaded");
        }
    }

    public static Set<Class<?>> getClasses(String packageName) throws ClassNotFoundException {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        String packageNamePath = packageName.replace(".", "/");
        String pkg_name = packageName.substring(packageName.lastIndexOf(".")+1);

        File packageDir = new File(packageNamePath);
        if (!packageDir.exists()) {
            return classes;
        }

        File[] files = packageDir.listFiles();
        if (files == null) {
            return classes;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                Set<Class<?>> subClasses = getClasses(packageName + "." + file.getName());
                classes.addAll(subClasses);
            } else {
                String className = file.getName().substring(0,file.getName().lastIndexOf("."));
                String reflect_var = pkg_name + "." + className;
                Class<?> clazz = Class.forName(reflect_var);
                classes.add(clazz);
            }
        }

        return classes;
    }

}
