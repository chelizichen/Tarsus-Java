
package com.tarsus.dev_v2_0.struct;

import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusJsonInf;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;


@TaroStruct
public class SetCacheRes extends TarsusJsonInf {
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
  