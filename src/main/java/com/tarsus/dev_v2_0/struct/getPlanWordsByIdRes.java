package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class getPlanWordsByIdRes extends TarsusBodyABS {
    public PlanWords data;
    public Integer code;
    public String message;


    // ListConstructor
    public getPlanWordsByIdRes(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "getPlanWordsByIdRes");
        this.data = _tarsusStream.read_struct(1, "PlanWords");
        this.code = _tarsusStream.read_int(2);
        this.message = _tarsusStream.read_string(3);

    }

    // NoArgsConstructor
    public getPlanWordsByIdRes() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  