
package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class GetUserByIdReq extends TarsusBodyABS {
    public Integer id;
    public Basic basic;


    // ListConstructor
    public GetUserByIdReq(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "GetUserByIdReq");
        this.id = _tarsusStream.read_int(1);
        this.basic = _tarsusStream.read_struct(2, "Basic");

    }

    // NoArgsConstructor
    public GetUserByIdReq() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
