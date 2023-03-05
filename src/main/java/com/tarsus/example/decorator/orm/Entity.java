package com.tarsus.example.decorator.orm;

import com.tarsus.example.decorator.TarsusParam;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.CONSTRUCTOR})
@TarsusParam
public @interface Entity {

}
