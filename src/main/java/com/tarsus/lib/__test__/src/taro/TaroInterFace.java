package com.tarsus.lib.__test__.src.taro;


import com.tarsus.lib.__test__.src.struct.*;

public interface TaroInterFace {
    public int getUserById(GetUserByIdReq req, GetUserByIdRes res);

    public int getUserList(GetUserListReq req, GetUserListRes res);

}
