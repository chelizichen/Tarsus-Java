package com.tarsus.example.base;


import com.tarsus.example.config.ret;
import com.tarsus.example.decorator.TarsusMethod;
import com.tarsus.example.decorator.TaroStruct;
import com.tarsus.example.decorator.ioc.Collect;
import com.tarsus.example.decorator.ioc.Inject;
import com.tarsus.example.decorator.ioc.Mapper;
import com.tarsus.example.decorator.ioc.Service;
import com.tarsus.example.decorator.orm.Entity;
import com.tarsus.example.decorator.TarsusInterFace;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author leemulus
 * @since 2023.2.16
 * 所有Rpc控制类基于此类
 */
public class TarsusBaseInterFace {
    protected static Map<String, TarsusBaseInterFace> ClazzMap = new HashMap();
    protected static Map<String, Class<?>> ParamsMap = new HashMap<>();
    protected static Map<String, Method> MethodsMap = new HashMap<>();
    protected static Map<String, Object> IocMap = new HashMap<>();

    public String currentData = "";
    public Boolean lock = true;

    public String InterFace;

    public TarsusBaseInterFace() {
        System.out.println("this" + this.getClass().getSimpleName());
        boolean hasAnnotation = this.getClass().isAnnotationPresent(TarsusInterFace.class);
        String interFaceName_Or_ClazzName = "";
        if (hasAnnotation) {
            TarsusInterFace testAnnotation = this.getClass().getAnnotation(TarsusInterFace.class);
            interFaceName_Or_ClazzName = testAnnotation.interFace();
        } else {
            interFaceName_Or_ClazzName = this.getClass().getSimpleName();
        }
        TarsusBaseInterFace.ClazzMap.put(interFaceName_Or_ClazzName, this);
        this.InterFace = interFaceName_Or_ClazzName;
        // 单独设置参数
        this.SetParams();

        // 单独设置IOC
        this.SetIocService();
    }

    /**
     * @author chelizichen
     * @since 2023.2.16
     * use ioc to split code
     * use single
     */
    private void SetIocService() {
        final Field[] declaredFields = this.getClass().getDeclaredFields();
        for (Field field :
                declaredFields) {
            final boolean isInject = field.isAnnotationPresent(Inject.class);
            // 判断是否为 Dependency
            final boolean isCollect = field.getType().isAnnotationPresent(Collect.class);
            final boolean isAdoEntity = field.getType().isAnnotationPresent(Entity.class);
            final boolean isMapper = field.getType().isAnnotationPresent(Mapper.class);
            final boolean isService = field.getType().isAnnotationPresent(Service.class);

            if (isInject && (isCollect || isAdoEntity || isMapper || isService)) {
                final String simpleName = field.getType().getSimpleName();
                final Object instance = TarsusBaseInterFace.IocMap.get(simpleName);
                if (instance != null) {
                    field.setAccessible(true);
                    try {
                        field.set(this, instance);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        final Constructor<?> constructor = field.getType().getDeclaredConstructor();
                        try {
                            final Object ServiceInstance = constructor.newInstance();
                            field.setAccessible(true);
                            field.set(this, ServiceInstance);
                            final String serviceName = ServiceInstance.getClass().getSimpleName();
                            TarsusBaseInterFace.IocMap.put(serviceName, ServiceInstance);
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * @author leemulus
     * @since 2023.2.16
     * 用来设置公共参数类型
     * 加载阶段，将参数的类和类型别名加载到 ArcInterFace.ParamsMap 中
     * 运行阶段 通过注解中的 ApcParams.value 来获取对应类型 并加载
     */
    private void SetParams() {
        Method[] methods = this.getClass().getMethods();
        for (Method method : methods) {

            // 需要是 @ArcMethod 注解
            if (method.isAnnotationPresent(TarsusMethod.class)) {

                // 创建 ObServer，将 Name + 方法名称带入;
                TarsusBaseInterFace.MethodsMap.put(this.InterFace + method.getName(), method);

                final Class<?>[] parameterTypes = method.getParameterTypes();

                for (Class<?> parameterType : parameterTypes) {
                    final boolean annotationPresent = parameterType.isAnnotationPresent(TaroStruct.class);
                    if (annotationPresent) {
                        final TaroStruct annotation = parameterType.getAnnotation(TaroStruct.class);
                        final String value = annotation.value();

                        // 当没有设置参数时
                        if (value.equals("")) {
                            final String simpleName = parameterType.getSimpleName();
                            TarsusBaseInterFace.ParamsMap.put(simpleName, parameterType);
                        } else {
                            TarsusBaseInterFace.ParamsMap.put(value, parameterType);
                        }
                    }
                }
            }
        }
    }

    /**
     * 传过来的一个args 参数
     * 遍历 args 将每个 args 进行类型转换 作为新的类型参数传递给 已存储的方法中实现调用
     */
    public String invokeMethod(String interFace, String method, List<Object> args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println("List|args" + args);
        // 拿到对应方法
        final Method getMethod = TarsusBaseInterFace.MethodsMap.get(interFace + method);
        // 拿到对应多少个参数
        // 在TarsusStream 与 Taro 完善的情况下将只使用两个参数
        final Class<?>[] parameterTypes = getMethod.getParameterTypes();
        // 由于默认两个，但是因为某些bug，会传入三个过来，所以 List 设置的 长度为 size - 1
        final ArrayList<Object> truthParams = new ArrayList<>(args.size() - 1); // 实参
        List<Object> argList = (List<Object>) args.get(0);


        Class<?> request = TarsusBaseInterFace.ParamsMap.get(parameterTypes[0].getSimpleName());
        Constructor<?> declaredConstructor = request.getConstructor(List.class);
        Object args1 = declaredConstructor.newInstance(argList);
        truthParams.add(args1);

        String response = (String) args.get(1);
        String simpleName = parameterTypes[1].getSimpleName();
        if(response.equals(simpleName)){
            Class<?> responseClass = TarsusBaseInterFace.ParamsMap.get(parameterTypes[1].getSimpleName());
            Constructor<?> noArgsConst = responseClass.getConstructor();
            Object args2 = noArgsConst.newInstance();
            truthParams.add(args2);
        }




        final TarsusBaseInterFace tarsusBaseInterFace = TarsusBaseInterFace.ClazzMap.get(interFace);
        return this.__invoke__( tarsusBaseInterFace, truthParams, getMethod);
    }

    private String __invoke__(TarsusBaseInterFace tarsusBaseInterFace, List<Object> truthParams, Method getMethod) throws InvocationTargetException, IllegalAccessException {
        String retVal = "";
        getMethod.invoke(tarsusBaseInterFace, truthParams.get(0), truthParams.get(1));

        while (!lock) {
            retVal = this.currentData;
            this.currentData = "";
            this.lock = true;
        }
        return retVal;
    }

    public void TaroRet(String data) {
        this.currentData = data;
        this.lock = false;
    }
}