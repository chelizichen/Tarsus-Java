package dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class setRecordReq extends TarsusBodyABS {
    public Integer user_id;
    public String record_time;


    // ListConstructor
    public setRecordReq(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "setRecordReq");
        this.user_id = _tarsusStream.read_int(1);
        this.record_time = _tarsusStream.read_string(2);

    }

    // NoArgsConstructor
    public setRecordReq() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  