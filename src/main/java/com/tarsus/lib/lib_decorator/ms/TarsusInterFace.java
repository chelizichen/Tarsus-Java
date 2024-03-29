package com.tarsus.lib.lib_decorator.ms;

import java.lang.annotation.*;

/**
 * 用于定义微服务接口的注解
 * 注册微服务接口，在调用时
 * @since 2023.2.16
 * @author chelizichen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.CONSTRUCTOR})
@Inherited
public @interface TarsusInterFace {
    String value() default "" ;
}
