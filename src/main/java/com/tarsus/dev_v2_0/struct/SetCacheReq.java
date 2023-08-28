
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
public class SetCacheReq implements TarsusJsonInf {
    public String key;
    public String value;
    public List<String> keys;
    public List<String> values;


    // ListConstructor
    public SetCacheReq(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "SetCacheReq");
        this.key = _tarsusStream.read_string(1);
        this.value = _tarsusStream.read_string(2);
        this.keys = _tarsusStream.read_list(3, "List<string>");
        this.values = _tarsusStream.read_list(4, "List<string>");

    }

    // NoArgsConstructor
    public SetCacheReq() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  