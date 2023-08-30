package com.tarsus.dev_v2_0.interfaces;

import com.tarsus.dev_v2_0.struct.*;
import com.tarsus.dev_v2_0.taro.UserInf;
import com.tarsus.lib.lib_decorator.ms.TarsusInterFace;
import com.tarsus.lib.lib_decorator.ms.TarsusReflect;
import com.tarsus.lib.main_control.load_server.impl.TarsusBaseReflect;

@TarsusReflect(proxy = "UserServer",interFace = "user")
public class UserImpl extends TarsusBaseReflect implements UserInf {
    @Override
    public queryUsersNameRes getBaseUserInfoList(queryIdsReq req, queryUsersNameRes res) {
        return null;
    }

    @Override
    public getUserListRes getUserList(getUserListReq req, getUserListRes res) {
        return null;
    }

    @Override
    public getUserByIdRes getUserById(queryIdReq req, getUserByIdRes res) {
        return null;
    }

    @Override
    public baseRes delUserById(queryIdReq req, baseRes res) {
        return null;
    }

    @Override
    public baseRes saveUser(User req, baseRes res) {
        return null;
    }

    @Override
    public baseRes batchDelUser(queryIdsReq req, baseRes res) {
        return null;
    }

    @Override
    public baseRes batchSetUser(batchSetUserReq req, baseRes res) {
        return null;
    }
}
