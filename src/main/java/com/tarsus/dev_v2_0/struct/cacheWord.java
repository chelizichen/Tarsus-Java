
package com.tarsus.dev_v2_0.struct;

import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusJsonInf;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;


@TaroStruct
public class cacheWord implements TarsusJsonInf {
    public String en_word;
    public String user_name;
    public String user_id;
    public String own_mark;


    // ListConstructor
    public cacheWord(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "cacheWord");
        this.en_word = _tarsusStream.read_string(1);
        this.user_name = _tarsusStream.read_string(2);
        this.user_id = _tarsusStream.read_string(3);
        this.own_mark = _tarsusStream.read_string(4);

    }

    // NoArgsConstructor
    public cacheWord() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  