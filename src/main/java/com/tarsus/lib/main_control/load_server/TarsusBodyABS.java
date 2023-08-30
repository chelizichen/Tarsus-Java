package com.tarsus.lib.main_control.load_server;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;

public abstract class TarsusBodyABS {
    @JSONField(serialize=false)
    public String __eid__ = "111";

    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }

}
