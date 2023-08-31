package dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class getUserListReq extends TarsusBodyABS {
    public String keyword;
    public Integer page;
    public Integer size;
    public String start_time;
    public String end_time;


    // ListConstructor
    public getUserListReq(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "getUserListReq");
        this.keyword = _tarsusStream.read_string(1);
        this.page = _tarsusStream.read_int(2);
        this.size = _tarsusStream.read_int(3);
        this.start_time = _tarsusStream.read_string(4);
        this.end_time = _tarsusStream.read_string(5);

    }

    // NoArgsConstructor
    public getUserListReq() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  