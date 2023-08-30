package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class Word extends TarsusBodyABS {
    public Integer id;
    public String en_name;
    public String create_time;
    public String own_mark;
    public String type;
    public Integer total_trans;
    public Integer user_id;
    public String word_translates;
    public String update_time;


    // ListConstructor
    public Word(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "Word");
        this.id = _tarsusStream.read_int(1);
        this.en_name = _tarsusStream.read_string(2);
        this.create_time = _tarsusStream.read_string(3);
        this.own_mark = _tarsusStream.read_string(4);
        this.type = _tarsusStream.read_string(5);
        this.total_trans = _tarsusStream.read_int(6);
        this.user_id = _tarsusStream.read_int(7);
        this.word_translates = _tarsusStream.read_string(8);
        this.update_time = _tarsusStream.read_string(9);

    }

    // NoArgsConstructor
    public Word() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  