
package dev_v1_0.struct;

import dev_v1_0.decorator.TaroStruct;
import dev_v1_0.base.inf.TarsusJson;
import dev_v1_0.base.TarsusStream;

import java.util.List;

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
