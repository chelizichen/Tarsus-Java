package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class queryIdReq extends TarsusBodyABS {
    public Integer id;


    // ListConstructor
    public queryIdReq(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "queryIdReq");
        this.id = _tarsusStream.read_int(1);

    }

    // NoArgsConstructor
    public queryIdReq() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  