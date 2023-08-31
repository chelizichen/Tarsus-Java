package dev_v1_0.base;


import dev_v1_0.base.err.TarsusErr;
import dev_v1_0.base.inf.TarsusJson;
import dev_v1_0.decorator.TarsusInterFace;
import dev_v1_0.decorator.TarsusMethod;
import dev_v1_0.decorator.ioc.Collect;
import dev_v1_0.decorator.ioc.Inject;
import dev_v1_0.decorator.ioc.Mapper;
import dev_v1_0.decorator.ioc.Service;
import dev_v1_0.decorator.orm.Entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
            interFaceName_Or_ClazzName = testAnnotation.value();
        } else {
            interFaceName_Or_ClazzName = this.getClass().getSimpleName();
        }
        TarsusBaseInterFace.ClazzMap.put(interFaceName_Or_ClazzName, this);
        this.InterFace = interFaceName_Or_ClazzName;
        // 单独设置参数

        // 单独设置IOC
        this.SetIocService();

        Method[] methods = this.getClass().getMethods();
        for (Method method : methods) {
            // 需要是 @TarsusMethod 注解
            if (method.isAnnotationPresent(TarsusMethod.class)) {
                // 创建 ObServer，将 Name + 方法名称带入;
                TarsusBaseInterFace.MethodsMap.put(this.InterFace + method.getName(), method);
            }
        }
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
     * 传过来的一个args 参数
     * 遍历 args 将每个 args 进行类型转换 作为新的类型参数传递给 已存储的方法中实现调用
     */
    public String invokeMethod(String interFace, String method, List<Object> args,String _request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final Method getMethod = TarsusBaseInterFace.MethodsMap.get(interFace + method);
        final Class<?>[] parameterTypes = getMethod.getParameterTypes();
        final ArrayList<Object> truthParams = new ArrayList<>(); // 实参

        final String request_clazz_name = parameterTypes[0].getSimpleName();

        // 判断请求参数是否一致
        if(!request_clazz_name.equals(_request)){
            return TarsusErr.Request(request_clazz_name,_request);
        }

        Class<?> request = TarsusStream.StreamMap.get(parameterTypes[0].getSimpleName());
        Constructor<?> declaredConstructor = request.getConstructor(List.class);

        // 请求Request
        Object RequestInstance = declaredConstructor.newInstance(args);
        truthParams.add(RequestInstance);

        Class<?> responseClass = TarsusStream.StreamMap.get(parameterTypes[1].getSimpleName());
        Constructor<?> noArgsConst = responseClass.getConstructor();
        Object args2 = noArgsConst.newInstance();
        truthParams.add(args2);

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

    public <T extends TarsusJson>void json(T data) {
        this.currentData = data.json();
        this.lock = false;
    }
}
