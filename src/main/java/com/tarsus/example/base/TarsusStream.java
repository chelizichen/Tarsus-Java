// 线性表中拿到对应的数据并自动生成类的实例
// 由 *.taro 自动生成类和接口
package com.tarsus.example.base;
import com.tarsus.example.base.inf.TarsusStreamInf;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TarsusStream implements TarsusStreamInf {
    public List<?> arguments;
    public String clazzName;
    public Class TargetClass;
    public static List<String> base_type = Arrays.asList("int", "string", "bool");

    public static HashMap<String,Class<?>> StreamMap = new HashMap<>();

    public static void SetClass(Class<?> clazz) {
        final String simpleName = clazz.getSimpleName();
        StreamMap.put(simpleName,clazz);
    }
    public static Class<?> GetClass(String clazzName){
        return StreamMap.get(clazzName);
    }

    // 强转类型为 String
    public TarsusStream(List<?> list,String clazzName){
        this.arguments =  list;
        this.clazzName = clazzName;
        this.TargetClass = TarsusStream.GetClass(clazzName);
    }


    @Override
    public String read_string(Integer index) {
        index = index -1;
        return String.valueOf(this.arguments.get(index));
    }

    @Override
    public Integer read_int(Integer index) {
        index = index -1;
        final Object o = this.arguments.get(index);
        if (o instanceof Integer){
            return (Integer) o;
        }
        final Integer integer = Integer.valueOf((String) this.arguments.get(index));
        return integer;
    }

    @Override
    public <T>List<T> read_list(Integer index, String className) {
        index = index -1;
        String getType = GetType(className);
        List<T> args = new ArrayList<>();
        if(this.arguments.get(index) instanceof List){
            List<?> list_args = (List<?>) this.arguments.get(index);
            // 基础类型
            if(base_type.contains(getType)){
                for (int i = 0; i < list_args.size(); i++) {
                    args.add(this.read((String) list_args.get(i),getType));
                }
            }else {
                for (int i = 0; i < list_args.size(); i++) {
                    final Class<?> clazz = GetClass(getType);
                    // 线性表形式
                    List<?> ConstructorListArgs = (List<?>) list_args.get(i);
                    final Constructor<?> declaredConstructor;
                    try {
                        declaredConstructor = clazz.getDeclaredConstructor(List.class);
                        final T instance =(T) declaredConstructor.newInstance(ConstructorListArgs);
                        args.add(instance);
                    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            return args;
        }

        return args;
    }

    @Override
    public <T>T read_struct(Integer index, String className) {
        index = index -1;
        final Class<?> clazz = GetClass(className);
        List<?> list_args = (List<?>) this.arguments.get(index);
        try {
            final Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(List.class);
            final T instance = (T) declaredConstructor.newInstance(list_args);
            return instance;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T read(String value, String type) {
        if(type.equals("string")){
            return (T) value;
        }
        if(type.equals("int")){
            return (T) Integer.valueOf((String) value);
        }
        return null;
    }

    public static String GetType(String type){
        Pattern pattern = Pattern.compile("<(.+?)>");
        Matcher matcher = pattern.matcher(type);
        if (matcher.find()) {
            String match = matcher.group(1);
            return match;
        }
        return "";
    }
}

