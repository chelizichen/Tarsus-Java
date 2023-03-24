package com.tarsus.example.TEST.StreamTest;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.example.base.TarsusStream;

import java.util.List;

public class GetUserByIdRes {
    public Integer code;
    public String message;
    public User data;
    public List<User> users;

    public GetUserByIdRes(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "GetUserByIdRes");
        this.code = _tarsusStream.read_int(1);
        this.message = _tarsusStream.read_string(2);
        this.data = _tarsusStream.read_struct(3, "User");
        this.users = _tarsusStream.read_list(4,"List<User>");
    }

    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
