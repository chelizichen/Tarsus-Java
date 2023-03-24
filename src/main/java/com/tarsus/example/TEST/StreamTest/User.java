package com.tarsus.example.TEST.StreamTest;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.example.base.TarsusStream;

import java.util.List;

public class User {
    public String id;
    public String name;
    public String age;
    public String fullName;
    public String address;


    // ListConstructor
    public User(List<Object> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "User");
        this.id = _tarsusStream.read_string(1);
        this.name = _tarsusStream.read_string(2);
        this.age = _tarsusStream.read_string(3);
        this.fullName = _tarsusStream.read_string(4);
        this.address = _tarsusStream.read_string(5);
    }

    // NoArgsConstructor
    public User() {

    }

    // toJson
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
