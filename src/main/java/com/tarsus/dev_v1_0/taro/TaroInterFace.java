package com.tarsus.dev_v1_0.taro;

import com.tarsus.dev_v1_0.struct.GetUserByIdReq;
import com.tarsus.dev_v1_0.struct.GetUserByIdRes;
import com.tarsus.dev_v1_0.struct.GetUserListReq;
import com.tarsus.dev_v1_0.struct.GetUserListRes;

public interface TaroInterFace {
    public int getUserById(GetUserByIdReq req, GetUserByIdRes res);

    public int getUserList(GetUserListReq req, GetUserListRes res);

}
