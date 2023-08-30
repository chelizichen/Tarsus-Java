 
    package com.tarsus.dev_v2_0.taro;
    import com.tarsus.dev_v2_0.struct.*;

  public interface  ShareInf {
    public getShareListRes getShareList(getListBaseReq req, getShareListRes res);
public baseRes shareToUser(shareToUserReq req, baseRes res);
public baseRes starShare(starShareReq req, baseRes res);
public baseRes saveShare(ShareInfo req, baseRes res);
public baseRes saveShareDetail(ShareDetail req, baseRes res);
public baseRes delShare(queryIdReq req, baseRes res);

  }