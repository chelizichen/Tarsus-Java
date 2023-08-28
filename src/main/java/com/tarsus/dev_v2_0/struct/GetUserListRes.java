
package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusJsonInf;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class GetUserListRes extends TarsusJsonInf {
    public Integer code;
    public String message;
    public List<User> data;


    // ListConstructor
    public GetUserListRes(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "GetUserListRes");
        this.code = _tarsusStream.read_int(1);
        this.data = _tarsusStream.read_struct(2, "List<User>");
        this.message = _tarsusStream.read_string(3);

    }

    // NoArgsConstructor
    public GetUserListRes() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
