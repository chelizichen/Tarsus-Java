package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class PlanDetail extends TarsusBodyABS {
    public Integer id;
    public String user_id;
    public String is_mark;
    public String plan_start_time;
    public String plan_end_time;
    public String create_time;


    // ListConstructor
    public PlanDetail(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "PlanDetail");
        this.id = _tarsusStream.read_int(1);
        this.user_id = _tarsusStream.read_string(2);
        this.is_mark = _tarsusStream.read_string(3);
        this.plan_start_time = _tarsusStream.read_string(4);
        this.plan_end_time = _tarsusStream.read_string(5);
        this.create_time = _tarsusStream.read_string(6);

    }

    // NoArgsConstructor
    public PlanDetail() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  