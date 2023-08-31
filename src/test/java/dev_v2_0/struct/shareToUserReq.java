package dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class shareToUserReq extends TarsusBodyABS {
    public Integer share_id;
    public Integer user_id;
    public Integer to_user_id;


    // ListConstructor
    public shareToUserReq(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "shareToUserReq");
        this.share_id = _tarsusStream.read_int(1);
        this.user_id = _tarsusStream.read_int(2);
        this.to_user_id = _tarsusStream.read_int(3);

    }

    // NoArgsConstructor
    public shareToUserReq() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  