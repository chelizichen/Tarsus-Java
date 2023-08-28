
package com.tarsus.dev_v2_0.taro;

import com.tarsus.dev_v2_0.struct.*;

public interface CacheInf {
    public SetCacheRes setCache(SetCacheReq req, SetCacheRes res);

    public GetCacheRes GetCache(GetCacheReq req, GetCacheRes res);

    public getWordsByIdsRes getCacheWords(getWordsByIdsReq req, getWordsByIdsRes res);

}