package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class ShareDetail extends TarsusBodyABS {
    public Integer id;
    public String content;
    public String word_ids_list;
    public Integer share_id;
    public String update_time;


    // ListConstructor
    public ShareDetail(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "ShareDetail");
        this.id = _tarsusStream.read_int(1);
        this.content = _tarsusStream.read_string(2);
        this.word_ids_list = _tarsusStream.read_string(3);
        this.share_id = _tarsusStream.read_int(4);
        this.update_time = _tarsusStream.read_string(5);

    }

    // NoArgsConstructor
    public ShareDetail() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  