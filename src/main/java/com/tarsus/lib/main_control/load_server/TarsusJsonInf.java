package com.tarsus.lib.main_control.load_server;

import com.alibaba.fastjson.JSONObject;

public abstract class TarsusJsonInf {
    public String __eid__;
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }

}
