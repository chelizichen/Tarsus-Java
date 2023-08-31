 
    package dev_v2_0.taro;
    import dev_v2_0.struct.*;

    public interface  RecordInf {
    public getUserRecordRes getUserRecord(queryIdReq req, getUserRecordRes res);
public baseRes addUserRecord(queryIdReq req, baseRes res);
public baseRes delUserRecord(queryIdReq req, baseRes res);
public baseRes setRecord(setRecordReq req, baseRes res);
public getCurrRecordLenRes getCurrRecordLen(queryIdReq req, getCurrRecordLenRes res);

  }