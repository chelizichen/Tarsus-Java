
package com.tarsus.example.struct;

import com.tarsus.example.decorator.TaroStruct;
import com.tarsus.example.base.inf.TarsusJson;
import com.tarsus.example.base.TarsusStream;

import java.util.List;

import com.alibaba.fastjson.JSONObject;


@TaroStruct
public class GetUserListReq implements TarsusJson {
    public String text;
    public Basic basic;
    public List<Integer> ids;


    // ListConstructor
    public GetUserListReq(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "GetUserListReq");
        this.basic = _tarsusStream.read_struct(1, "Basic");
        this.ids = _tarsusStream.read_list(2, "List<int>");

    }

    // NoArgsConstructor
    public GetUserListReq() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
