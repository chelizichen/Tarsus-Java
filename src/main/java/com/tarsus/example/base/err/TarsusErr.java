package com.tarsus.example.base.err;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.example.base.inf.TarsusJson;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class TarsusErr<T> implements TarsusJson {
    public String message;
    public Integer code;
    public T data;

    public static String Request(String need,String params){
        return new TarsusErr<String>("Tarsus-Error :  请求参数不匹配" + need + " != " + params,-500,"").json();
    }


    @Override
    public String json() {
        return JSONObject.toJSON(this).toString();
    }
}
