
package com.tarsus.example.struct;

import com.tarsus.example.decorator.TaroStruct;
import com.tarsus.example.base.inf.TarsusJson;
import com.tarsus.example.base.TarsusStream;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;


@TaroStruct
public class User implements TarsusJson {
    public String id;
    public String name;
    public String age;
    public String fullName;
    public String address;


    // ListConstructor
    public User(List<?> list) {
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
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
