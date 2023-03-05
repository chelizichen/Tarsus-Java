package com.tarsus.example.utils;

public class TypeUtil {

    public static boolean isStringArray(Object target){
        final boolean equals = target.equals("String[]");
        return equals;
    }
    public static boolean isString(Object target){
        return target.equals("String");
    }
}
