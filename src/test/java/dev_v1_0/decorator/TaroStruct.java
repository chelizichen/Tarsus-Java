package dev_v1_0.decorator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @since 2023.2.17
 * 废弃 ArcSort 注解
 * 需要手动调用 构造函数(List<String> list) 来为成员变量赋值
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.PARAMETER,ElementType.CONSTRUCTOR})
public @interface TaroStruct {
    String value() default "";
}
