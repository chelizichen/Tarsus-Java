package com.tarsus.example.register;

import com.tarsus.example.base.TarsusBaseInterFace;
import com.tarsus.example.config.ret;
import com.tarsus.example.decorator.TarsusInterFace;
import com.tarsus.example.decorator.TarsusMethod;
import com.tarsus.example.decorator.TaroStruct;
import com.tarsus.example.taro.GetUserByIdReq;
import com.tarsus.example.taro.GetUserByIdRes;
import com.tarsus.example.taro.User;

@TarsusInterFace(interFace = "TaroTest")
public class Taro  extends TarsusBaseInterFace {
    @TarsusMethod
    public ret TaroDemoTest(String test,@TaroStruct GetUserByIdReq req ,@TaroStruct  GetUserByIdRes getUserByIdRes){
        System.out.println(test);
        System.out.println(req.id);
        System.out.println(req.basic);
        System.out.println(getUserByIdRes.code);
        System.out.println(getUserByIdRes.message);
        System.out.println(getUserByIdRes.data.address);
        System.out.println(getUserByIdRes.data.age);
        System.out.println(getUserByIdRes.data.fullName);
        System.out.println(getUserByIdRes.data.id);
        System.out.println(getUserByIdRes.data.name);

        return ret.success("11");
    }
}
