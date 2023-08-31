package dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class baseRes extends TarsusBodyABS {
    public Integer code;
    public String message;


    // ListConstructor
    public baseRes(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "baseRes");
        this.code = _tarsusStream.read_int(1);
        this.message = _tarsusStream.read_string(2);

    }

    // NoArgsConstructor
    public baseRes() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  