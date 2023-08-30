package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class queryIdsReq extends TarsusBodyABS {
    public List<Integer> ids;


    // ListConstructor
    public queryIdsReq(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "queryIdsReq");
        this.ids = _tarsusStream.read_list(1, "List<int>");

    }

    // NoArgsConstructor
    public queryIdsReq() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  