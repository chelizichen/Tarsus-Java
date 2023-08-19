package com.tarsus.lib.main_control.load_server.impl;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.main_control.load_server.TarsusJsonInf;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TarsusErr<T> implements TarsusJsonInf {
    public String message;
    public Integer code;
    public T data;

    public static String Request(String need, String params) {
        return new TarsusErr<String>("Tarsus-Error :  请求参数不匹配" + need + " != " + params, -500, "").json();
    }

    public static String Response(String get$res, String need$res) {
        return new TarsusErr<String>("Tarsus-Error : 返回的参数不匹配" + need$res + "!=" + get$res,-501,"").json();
    }


    @Override
    public String json() {
        return JSONObject.toJSON(this).toString();
    }
}
