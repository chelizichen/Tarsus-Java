package dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class getListBaseReq extends TarsusBodyABS {
    public String desc;
    public String keyword;
    public Integer page;
    public Integer size;
    public String start_time;
    public String end_time;
    public Integer user_id;
    public Integer type;


    // ListConstructor
    public getListBaseReq(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "getListBaseReq");
        this.desc = _tarsusStream.read_string(1);
        this.keyword = _tarsusStream.read_string(2);
        this.page = _tarsusStream.read_int(3);
        this.size = _tarsusStream.read_int(4);
        this.start_time = _tarsusStream.read_string(5);
        this.end_time = _tarsusStream.read_string(6);
        this.user_id = _tarsusStream.read_int(7);
        this.type = _tarsusStream.read_int(8);

    }

    // NoArgsConstructor
    public getListBaseReq() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  