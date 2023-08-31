package dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class starShareReq extends TarsusBodyABS {
    public Integer share_id;
    public Integer num;


    // ListConstructor
    public starShareReq(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "starShareReq");
        this.share_id = _tarsusStream.read_int(1);
        this.num = _tarsusStream.read_int(2);

    }

    // NoArgsConstructor
    public starShareReq() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  