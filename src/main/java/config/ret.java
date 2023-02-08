package config;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class ret<T> {
    public Integer code;
    public String msg;
    public T data;

    ret(T data){
        this.code = 0;
        this.msg = "ok";
        this.data = data;
    }

    @Override
    public String toString() {

        if(data instanceof Map){
            Object data = JSONObject.toJSON(this.data);
            return "{" +
                    "code:" + code +
                    ", msg:'" + msg + '\'' +
                    ", data:" + data +
                    '}';
        }
        return "{" +
                "code:" + code +
                ", msg:'" + msg + '\'' +
                ", data:" + data +
                '}';
    }
    public static <T>ret success(T data){
        return new ret(data);
    }


}
