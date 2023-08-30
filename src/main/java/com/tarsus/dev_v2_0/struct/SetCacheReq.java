package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class SetCacheReq extends TarsusBodyABS {
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
  