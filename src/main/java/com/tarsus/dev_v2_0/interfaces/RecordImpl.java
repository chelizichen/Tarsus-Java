package com.tarsus.dev_v2_0.interfaces;

import com.tarsus.dev_v2_0.struct.*;
import com.tarsus.dev_v2_0.taro.RecordInf;
import com.tarsus.lib.lib_decorator.ms.TarsusInterFace;
import com.tarsus.lib.lib_decorator.ms.TarsusMethod;

@TarsusInterFace("record")
public class RecordImpl implements RecordInf {
    @Override
    @TarsusMethod
    public getUserRecordRes getUserRecord(queryIdReq req, getUserRecordRes res) {
        return null;
    }

    @Override
    @TarsusMethod
    public baseRes addUserRecord(queryIdReq req, baseRes res) {
        return null;
    }

    @Override
    @TarsusMethod
    public baseRes delUserRecord(queryIdReq req, baseRes res) {
        return null;
    }

    @Override
    @TarsusMethod
    public baseRes setRecord(setRecordReq req, baseRes res) {
        return null;
    }

    @Override
    @TarsusMethod
    public getCurrRecordLenRes getCurrRecordLen(queryIdReq req, getCurrRecordLenRes res) {
        return null;
    }
}
