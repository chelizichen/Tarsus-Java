package com.tarsus.example.taro;

import com.tarsus.example.struct.GetUserByIdReq;
import com.tarsus.example.struct.GetUserByIdRes;
import com.tarsus.example.struct.GetUserListReq;
import com.tarsus.example.struct.GetUserListRes;

public interface TaroInterFace {
    public int getUserById(GetUserByIdReq req, GetUserByIdRes res);

    public int getUserList(GetUserListReq req, GetUserListRes res);

}
