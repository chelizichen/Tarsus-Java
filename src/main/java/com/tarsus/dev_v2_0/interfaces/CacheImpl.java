package com.tarsus.dev_v2_0.interfaces;

import com.tarsus.dev_v2_0.struct.*;
import com.tarsus.dev_v2_0.taro.CacheInf;
import com.tarsus.lib.lib_decorator.ms.TarsusReflect;
import com.tarsus.lib.main_control.load_server.impl.TarsusBaseReflect;

@TarsusReflect(proxy = "UserServer", interFace = "cache")
public class CacheImpl extends TarsusBaseReflect implements CacheInf {

    @Override
    public SetCacheRes setCache(SetCacheReq req, SetCacheRes res) {
        return null;
    }

    @Override
    public GetCacheRes GetCache(GetCacheReq req, GetCacheRes res) {
        return null;
    }

    @Override
    public getWordsByIdsRes getCacheWords(getWordsByIdsReq req, getWordsByIdsRes res) {
        return null;
    }
}
