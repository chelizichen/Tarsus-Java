package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class queryUsersNameRes extends TarsusBodyABS {
    public List<userBaseInfo> users;


    // ListConstructor
    public queryUsersNameRes(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "queryUsersNameRes");
        this.users = _tarsusStream.read_struct(1, "List<userBaseInfo>");

    }

    // NoArgsConstructor
    public queryUsersNameRes() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  