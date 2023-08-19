
package com.tarsus.dev_v1_0.struct;

import com.tarsus.dev_v1_0.decorator.TaroStruct;
import com.tarsus.dev_v1_0.base.inf.TarsusJson;
import com.tarsus.dev_v1_0.base.TarsusStream;

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
