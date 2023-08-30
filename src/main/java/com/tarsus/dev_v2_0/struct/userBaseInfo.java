package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class userBaseInfo extends TarsusBodyABS {
    public Integer id;
    public String user_name;


    // ListConstructor
    public userBaseInfo(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "userBaseInfo");
        this.id = _tarsusStream.read_int(1);
        this.user_name = _tarsusStream.read_string(2);

    }

    // NoArgsConstructor
    public userBaseInfo() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  