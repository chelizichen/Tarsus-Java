package com.tarsus.example.register;

import com.tarsus.example.base.TarsusBaseInterFace;
import com.tarsus.example.decorator.TarsusInterFace;
import com.tarsus.example.decorator.TarsusMethod;
import com.tarsus.example.struct.*;
import com.tarsus.example.taro.*;
import com.tarsus.example.struct.GetUserByIdReq;
import com.tarsus.example.struct.GetUserByIdRes;
import com.tarsus.example.struct.GetUserListRes;
import com.tarsus.example.struct.GetUserListReq;
import com.tarsus.example.struct.User;

@TarsusInterFace(interFace = "TaroInterFaceTest")
public class TaroInterFaceImpl extends TarsusBaseInterFace implements TaroInterFace {

    @Override
    @TarsusMethod
    public int getUserById(GetUserByIdReq req, GetUserByIdRes res) {
        res.data = new User();
        res.data.address ="1";
        res.data.age ="1";
        res.data.fullName ="1";
        res.data.id =req.basic.token;
        res.data.name ="1";
        res.code = 1121;
        res.message = req.basic.token;
        this.json(res);
        return 0;
    }

    @Override
    @TarsusMethod
    public int getUserList(GetUserListReq req, GetUserListRes res) {
        System.out.println(req.basic);
        System.out.println(req.ids);
        return 0;
    }
}
