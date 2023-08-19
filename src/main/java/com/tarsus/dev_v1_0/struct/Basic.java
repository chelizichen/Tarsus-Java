
package com.tarsus.dev_v1_0.struct;

import com.tarsus.dev_v1_0.decorator.TaroStruct;
import com.tarsus.dev_v1_0.base.inf.TarsusJson;
import com.tarsus.dev_v1_0.base.TarsusStream;

import java.util.List;

import com.alibaba.fastjson.JSONObject;


@TaroStruct
public class Basic implements TarsusJson {
    public String token;


    // ListConstructor
    public Basic(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "Basic");
        this.token = _tarsusStream.read_string(1);

    }

    // NoArgsConstructor
    public Basic() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
