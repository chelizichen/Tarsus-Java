package com.tarsus.lib.lib_decorator.ms;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.CONSTRUCTOR})
@Inherited
public @interface TarsusReflect {
    String proxy();
    String interFace();
}
