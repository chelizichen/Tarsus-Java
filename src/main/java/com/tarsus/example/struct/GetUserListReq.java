
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
        this.text = _tarsusStream.read_string(1);
        this.basic = _tarsusStream.read_struct(2, "Basic");
        this.ids = _tarsusStream.read_list(3, "List<int>");

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
