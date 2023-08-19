package com.tarsus.dev_v1_0.config;

import com.alibaba.fastjson.JSONObject;

public class ret<T> {
    public Integer code;
    public String msg;
    public T data;

    ret(T data,Integer code,String msg){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        Object o = JSONObject.toJSON(this);
        System.out.println(o.toString());
        return o.toString();
    }
    public static <T>ret success(T data){
        return new ret(data,0,"ok");
    }

    public static <T>ret error(T data){
        return new ret(data,-1,"ok");
    }


}
