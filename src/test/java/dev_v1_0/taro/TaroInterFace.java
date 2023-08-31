package dev_v1_0.taro;

import dev_v1_0.struct.GetUserByIdReq;
import dev_v1_0.struct.GetUserByIdRes;
import dev_v1_0.struct.GetUserListReq;
import dev_v1_0.struct.GetUserListRes;

public interface TaroInterFace {
    public int getUserById(GetUserByIdReq req, GetUserByIdRes res);

    public int getUserList(GetUserListReq req, GetUserListRes res);

}
