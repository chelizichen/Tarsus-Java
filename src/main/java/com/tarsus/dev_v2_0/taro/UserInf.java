 
    package com.tarsus.dev_v2_0.taro;
    import com.tarsus.dev_v2_0.struct.*;

  public interface  UserInf {
    public queryUsersNameRes getBaseUserInfoList(queryIdsReq req, queryUsersNameRes res);
public getUserListRes getUserList(getUserListReq req, getUserListRes res);
public getUserByIdRes getUserById(queryIdReq req, getUserByIdRes res);
public baseRes delUserById(queryIdReq req, baseRes res);
public baseRes saveUser(User req, baseRes res);
public baseRes batchDelUser(queryIdsReq req, baseRes res);
public baseRes batchSetUser(batchSetUserReq req, baseRes res);

  }