package dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class getUserPlansRes extends TarsusBodyABS {
    public List<PlanDetail> data;
    public Integer code;
    public String message;
    public Integer total;


    // ListConstructor
    public getUserPlansRes(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "getUserPlansRes");
        this.data = _tarsusStream.read_struct(1, "List<PlanDetail>");
        this.code = _tarsusStream.read_int(2);
        this.message = _tarsusStream.read_string(3);
        this.total = _tarsusStream.read_int(4);

    }

    // NoArgsConstructor
    public getUserPlansRes() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  