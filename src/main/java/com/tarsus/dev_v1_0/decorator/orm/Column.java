package com.tarsus.dev_v1_0.decorator.orm;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author leemulus
 * @since 2023.2.17
 * 设定数据库中对应的表名
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD})
public @interface Column {
    String value() default "";
}
