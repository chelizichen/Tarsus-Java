package com.tarsus.lib.main_control.load_manager;

import com.tarsus.lib.lib_decorator.ioc.Collect;
import com.tarsus.lib.lib_decorator.ioc.Inject;
import com.tarsus.lib.lib_decorator.ms.TarsusInterFace;
import com.tarsus.lib.lib_decorator.ms.TarsusMethod;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingletonRegistry {
    public static Map Ioc$Maps = new HashMap();
    public static Map Struct$Maps = new HashMap();
    public static Map Interface$Maps = new HashMap();

    public static Map<String,Method> Methods$Maps = new HashMap<String,Method>();

    public static Object invokeMethod() {
        return new Object();
    }

    @SneakyThrows
    public static void setMethods(Class<?>[] interfaces) {

        for (Class<?> get$interface : interfaces) {
            boolean is$annotation = get$interface.isAnnotationPresent(TarsusInterFace.class);
            // 没加装饰器报错
            if (!is$annotation) {
                continue;
//                throw new Error(get$interface.getSimpleName() + "is not annotation present as TarsusInterFace");
            }
            String interFace = get$interface.getAnnotation(TarsusInterFace.class).value();
            // 接口名报错
            if (interFace.equals("")) {
                throw new Error(get$interface.getSimpleName() + "- interface cannot set '' ");
            }
            // 新建实例
            Object instance = get$interface.getConstructor().newInstance();
            System.out.println("interface " + instance.getClass().getSimpleName() + " load success");

            SingletonRegistry.Interface$Maps.put(interFace, instance);

            Method[] declaredMethods = get$interface.getMethods();
            for (Method declaredMethod : declaredMethods) {
                if (!declaredMethod.isAnnotationPresent(TarsusMethod.class)) {
                    continue;
                }
                String name = interFace + declaredMethod.getName();
                System.out.println("methods " + name + " load success");
                SingletonRegistry.Methods$Maps.put(name, declaredMethod);
            }

        }
    }

    @SneakyThrows
    public static void setIoc$Maps(Object $instance) {
        Field[] declaredFields = $instance.getClass().getDeclaredFields();
        for (Field filed : declaredFields) {
            boolean is$Ioc = filed.isAnnotationPresent(Inject.class) || filed.isAnnotationPresent(Collect.class);
            if (is$Ioc) {
                return;
            }

            String name = filed.getType().getSimpleName();

            if (SingletonRegistry.Ioc$Maps.get(name) != null) {
                return;
            }
            Constructor<?> declaredConstructor = filed.getClass().getDeclaredConstructor();
            Object instance = declaredConstructor.newInstance();
            SingletonRegistry.Ioc$Maps.put(name, instance);
            filed.setAccessible(true);
        }
    }

    @Getter
    @Setter
    static class RequestParam {
        public String interFace;
        public String method;
        public List<?> args;
        public String request;
        public Method invokeMethod;

        public RequestParam(String interFace, String method, List<?> args, String request, Method invokeMethod) {
            this.args = args;
            this.interFace = interFace;
            this.method = method;
            this.request = request;
            this.invokeMethod = invokeMethod;
        }
    }

}
