
package com.tarsus.dev_v2_0.struct;

import com.tarsus.lib.lib_decorator.struct.TaroStruct;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.util.List;

import com.alibaba.fastjson.JSONObject;


@TaroStruct
public class getWordsByIdsRes extends TarsusBodyABS {
    public Integer code;
    public String message;
    public List<cacheWord> list;
    public Integer total;


    // ListConstructor
    public getWordsByIdsRes(List<?> list) {
        TarsusStream _tarsusStream = new TarsusStream(list, "getWordsByIdsRes");
        this.code = _tarsusStream.read_int(1);
        this.message = _tarsusStream.read_string(2);
        this.list = _tarsusStream.read_struct(3, "List<cacheWord>");
        this.total = _tarsusStream.read_int(4);

    }

    // NoArgsConstructor
    public getWordsByIdsRes() {

    }

    // toJson
    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }
}
  