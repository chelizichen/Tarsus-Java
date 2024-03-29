package dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class cacheWord extends TarsusBodyABS {
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
  