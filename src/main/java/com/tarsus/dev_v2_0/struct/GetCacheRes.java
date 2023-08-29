
package com.tarsus.dev_v2_0.struct;

import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;

import com.alibaba.fastjson.JSONObject;


@TaroStruct
public class GetCacheRes extends TarsusBodyABS {
    public String value;
    public List<String> values;


    // ListConstructor
    public GetCacheRes(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "GetCacheRes");
        this.value = _tarsusStream.read_string(1);
        this.values = _tarsusStream.read_list(2, "List<string>");

    }

    // NoArgsConstructor
    public GetCacheRes() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  