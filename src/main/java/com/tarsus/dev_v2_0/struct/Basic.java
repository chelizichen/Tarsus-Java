
package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusJsonInf;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class Basic implements TarsusJsonInf {
    public String token;


    // ListConstructor
    public Basic(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "Basic");
        this.token = _tarsusStream.read_string(1);

    }

    // NoArgsConstructor
    public Basic() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
