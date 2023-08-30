package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class ShareInfo extends TarsusBodyABS {
    public Integer id;
    public Integer user_id;
    public String create_time;
    public String img;
    public String update_time;


    // ListConstructor
    public ShareInfo(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "ShareInfo");
        this.id = _tarsusStream.read_int(1);
        this.user_id = _tarsusStream.read_int(2);
        this.create_time = _tarsusStream.read_string(3);
        this.img = _tarsusStream.read_string(4);
        this.update_time = _tarsusStream.read_string(5);

    }

    // NoArgsConstructor
    public ShareInfo() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  