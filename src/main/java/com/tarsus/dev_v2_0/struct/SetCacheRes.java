package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class SetCacheRes extends TarsusBodyABS {
    public String code;
    public String message;


    // ListConstructor
    public SetCacheRes(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "SetCacheRes");
        this.code = _tarsusStream.read_string(1);
        this.message = _tarsusStream.read_string(2);

    }

    // NoArgsConstructor
    public SetCacheRes() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  