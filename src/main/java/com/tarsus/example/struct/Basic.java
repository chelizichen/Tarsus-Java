
package com.tarsus.example.struct;

import com.tarsus.example.decorator.TaroStruct;
import com.tarsus.example.base.inf.TarsusJson;
import com.tarsus.example.base.TarsusStream;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

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
