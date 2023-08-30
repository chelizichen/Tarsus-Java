package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class WordTranslate extends TarsusBodyABS {
    public Integer id;
    public String cn_name;
    public String en_type;
    public String own_mark;
    public String create_time;
    public Integer word_id;
    public Integer user_id;


    // ListConstructor
    public WordTranslate(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "WordTranslate");
        this.id = _tarsusStream.read_int(1);
        this.cn_name = _tarsusStream.read_string(2);
        this.en_type = _tarsusStream.read_string(3);
        this.own_mark = _tarsusStream.read_string(4);
        this.create_time = _tarsusStream.read_string(5);
        this.word_id = _tarsusStream.read_int(6);
        this.user_id = _tarsusStream.read_int(7);

    }

    // NoArgsConstructor
    public WordTranslate() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  