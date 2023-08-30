package com.tarsus.dev_v2_0.struct;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;


@TaroStruct
public class getTranslateListRes extends TarsusBodyABS {
    public Integer code;
    public List<WordTranslate> list;
    public Integer total;


    // ListConstructor
    public getTranslateListRes(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "getTranslateListRes");
        this.code = _tarsusStream.read_int(1);
        this.list = _tarsusStream.read_struct(2, "List<WordTranslate>");
        this.total = _tarsusStream.read_int(3);

    }

    // NoArgsConstructor
    public getTranslateListRes() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  