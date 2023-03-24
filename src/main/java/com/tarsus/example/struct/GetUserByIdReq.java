
package com.tarsus.example.struct;

import com.tarsus.example.decorator.TaroStruct;
import com.tarsus.example.base.inf.TarsusJson;
import com.tarsus.example.base.TarsusStream;

import java.util.List;

import com.alibaba.fastjson.JSONObject;


@TaroStruct
public class GetUserByIdReq implements TarsusJson {
    public Integer id;
    public Basic basic;


    // ListConstructor
    public GetUserByIdReq(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "GetUserByIdReq");
        this.id = _tarsusStream.read_int(1);
        this.basic = _tarsusStream.read_struct(2, "Basic");

    }

    // NoArgsConstructor
    public GetUserByIdReq() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
