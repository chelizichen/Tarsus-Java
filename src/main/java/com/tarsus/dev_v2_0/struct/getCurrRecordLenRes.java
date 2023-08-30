package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class getCurrRecordLenRes extends TarsusBodyABS {
    public Integer record_length;
    public Integer user_name;
    public Integer code;
    public Integer message;


    // ListConstructor
    public getCurrRecordLenRes(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "getCurrRecordLenRes");
        this.record_length = _tarsusStream.read_int(1);
        this.user_name = _tarsusStream.read_int(2);
        this.code = _tarsusStream.read_int(3);
        this.message = _tarsusStream.read_int(4);

    }

    // NoArgsConstructor
    public getCurrRecordLenRes() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  