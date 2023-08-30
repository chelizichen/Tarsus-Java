package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class User extends TarsusBodyABS {
    public Integer id;
    public String username;
    public String password;
    public String email;
    public String phone;
    public String role_name;
    public String level;
    public String create_time;
    public String update_time;


    // ListConstructor
    public User(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "User");
        this.id = _tarsusStream.read_int(1);
        this.username = _tarsusStream.read_string(2);
        this.password = _tarsusStream.read_string(3);
        this.email = _tarsusStream.read_string(4);
        this.phone = _tarsusStream.read_string(5);
        this.role_name = _tarsusStream.read_string(6);
        this.level = _tarsusStream.read_string(7);
        this.create_time = _tarsusStream.read_string(8);
        this.update_time = _tarsusStream.read_string(9);

    }

    // NoArgsConstructor
    public User() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  