
package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusJsonInf;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;


import java.util.List;


@TaroStruct
public class GetUserByIdRes implements TarsusJsonInf {
    public Integer code;
    public User data;
    public String message;


    // ListConstructor
    public GetUserByIdRes(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "GetUserByIdRes");
        this.code = _tarsusStream.read_int(1);
        this.data = _tarsusStream.read_struct(2, "User");
        this.message = _tarsusStream.read_string(3);

    }

    // NoArgsConstructor
    public GetUserByIdRes() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
