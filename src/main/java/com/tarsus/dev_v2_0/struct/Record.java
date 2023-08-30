package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class Record extends TarsusBodyABS {
    public Integer id;
    public String create_time;
    public String is_register;
    public String user_id;


    // ListConstructor
    public Record(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "Record");
        this.id = _tarsusStream.read_int(1);
        this.create_time = _tarsusStream.read_string(2);
        this.is_register = _tarsusStream.read_string(3);
        this.user_id = _tarsusStream.read_string(4);

    }

    // NoArgsConstructor
    public Record() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  