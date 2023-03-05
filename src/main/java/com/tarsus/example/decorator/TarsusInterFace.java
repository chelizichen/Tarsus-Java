package com.tarsus.example.decorator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于定义微服务接口的注解
 * 注册微服务接口，在调用时
 * @since 2023.2.16
 * @author chelizichen
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.CONSTRUCTOR})
public @interface TarsusInterFace {
    public String interFace();
}
