
package com.tarsus.dev_v2_0.taro;

import com.tarsus.dev_v2_0.struct.*;

public interface TaroInterFace {
    public GetUserByIdRes getUserById(GetUserByIdReq req, GetUserByIdRes res);

    public GetUserListRes getUserList(GetUserListReq req, GetUserListRes res);

}