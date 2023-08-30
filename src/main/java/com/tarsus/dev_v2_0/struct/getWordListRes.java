package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class getWordListRes extends TarsusBodyABS {
    public Integer code;
    public List<Word> list;
    public Integer total;


    // ListConstructor
    public getWordListRes(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "getWordListRes");
        this.code = _tarsusStream.read_int(1);
        this.list = _tarsusStream.read_struct(2, "List<Word>");
        this.total = _tarsusStream.read_int(3);

    }

    // NoArgsConstructor
    public getWordListRes() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  