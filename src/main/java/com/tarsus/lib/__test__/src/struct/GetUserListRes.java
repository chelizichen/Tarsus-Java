
package com.tarsus.example.struct;

import com.tarsus.example.decorator.TaroStruct;
import com.tarsus.example.base.inf.TarsusJson;
import com.tarsus.example.base.TarsusStream;

import java.util.List;

import com.alibaba.fastjson.JSONObject;


@TaroStruct
public class GetUserListRes implements TarsusJson {
    public Integer code;
    public String message;
    public List<User> data;


    // ListConstructor
    public GetUserListRes(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "GetUserListRes");
        this.code = _tarsusStream.read_int(1);
        this.data = _tarsusStream.read_struct(2, "List<User>");
        this.message = _tarsusStream.read_string(3);

    }

    // NoArgsConstructor
    public GetUserListRes() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
