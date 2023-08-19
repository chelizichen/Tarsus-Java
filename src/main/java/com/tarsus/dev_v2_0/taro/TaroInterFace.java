package com.tarsus.dev_v2_0.taro;


import com.tarsus.dev_v2_0.struct.*;

public interface TaroInterFace {
    public int getUserById(GetUserByIdReq req, GetUserByIdRes res);

    public int getUserList(GetUserListReq req, GetUserListRes res);

}
