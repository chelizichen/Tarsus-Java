package dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class PlanWords extends TarsusBodyABS {
    public String word_ids;
    public String mark_date;
    public Integer is_mark;
    public String user_id;
    public String plan_id;
    public Integer id;


    // ListConstructor
    public PlanWords(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "PlanWords");
        this.word_ids = _tarsusStream.read_string(1);
        this.mark_date = _tarsusStream.read_string(2);
        this.is_mark = _tarsusStream.read_int(3);
        this.user_id = _tarsusStream.read_string(4);
        this.plan_id = _tarsusStream.read_string(5);
        this.id = _tarsusStream.read_int(6);

    }

    // NoArgsConstructor
    public PlanWords() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  