package dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class batchSetUserReq extends TarsusBodyABS {
    public List<Integer> ids;
    public User info;


    // ListConstructor
    public batchSetUserReq(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "batchSetUserReq");
        this.ids = _tarsusStream.read_list(1, "List<int>");
        this.info = _tarsusStream.read_struct(2, "User");

    }

    // NoArgsConstructor
    public batchSetUserReq() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  