package dev_v1_0.TEST.StreamTest;

import com.alibaba.fastjson.JSONObject;
import dev_v1_0.base.TarsusStream;

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
