
package com.tarsus.example.struct;

import com.tarsus.example.decorator.TaroStruct;
import com.tarsus.example.base.inf.TarsusJson;
import com.tarsus.example.base.TarsusStream;

import java.util.List;

import com.alibaba.fastjson.JSONObject;


@TaroStruct
public class GetUserByIdRes implements TarsusJson {
    public Integer code;
    public User data;
    public String message;


    // ListConstructor
    public GetUserByIdRes(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "GetUserByIdRes");
        this.code = _tarsusStream.read_int(1);
        this.data = _tarsusStream.read_struct(2, "User");
        this.message = _tarsusStream.read_string(3);

    }

    // NoArgsConstructor
    public GetUserByIdRes() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
