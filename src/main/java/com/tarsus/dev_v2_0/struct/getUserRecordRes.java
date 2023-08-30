package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class getUserRecordRes extends TarsusBodyABS {
    public Integer user_id;
    public List<Record> data;
    public Integer code;
    public Integer total;
    public String message;


    // ListConstructor
    public getUserRecordRes(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "getUserRecordRes");
        this.user_id = _tarsusStream.read_int(1);
        this.data = _tarsusStream.read_struct(2, "List<Record>");
        this.code = _tarsusStream.read_int(3);
        this.total = _tarsusStream.read_int(4);
        this.message = _tarsusStream.read_string(5);

    }

    // NoArgsConstructor
    public getUserRecordRes() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  