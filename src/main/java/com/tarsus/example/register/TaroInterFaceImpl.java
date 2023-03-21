package com.tarsus.example.register;

import com.tarsus.example.base.TarsusBaseInterFace;
import com.tarsus.example.decorator.TarsusInterFace;
import com.tarsus.example.decorator.TarsusMethod;
import com.tarsus.example.taro.*;

@TarsusInterFace(interFace = "TaroInterFaceTest")
public class TaroInterFaceImpl extends TarsusBaseInterFace implements TaroInterFace {
    
    @Override
    @TarsusMethod
    public int getUserById( GetUserByIdReq req, GetUserByIdRes res) {
        res.data = new User();
        res.data.address ="1";
        res.data.age ="1";
        res.data.fullName ="1";
        res.data.id =req.basic.token;
        res.data.name ="1";
        res.code = 1121;
        res.message = req.basic.token;
        this.TaroRet(res.json());
        return 0;
    }

    @Override
    public int getUserList(GetUserListReq req, GetUserListRes res) {
        return 0;
    }
}
