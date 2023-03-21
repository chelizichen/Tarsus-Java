package com.tarsus.example.register;

import com.tarsus.example.base.TarsusBaseInterFace;
import com.tarsus.example.decorator.TaroStruct;
import com.tarsus.example.decorator.TarsusInterFace;
import com.tarsus.example.decorator.TarsusMethod;
import com.tarsus.example.taro.*;

@TarsusInterFace(interFace = "TaroInterFaceTest")
public class TaroInterFaceImpl extends TarsusBaseInterFace implements TaroInterFace {

    @Override
    @TarsusMethod
    public int getUserById(@TaroStruct GetUserByIdReq req, @TaroStruct GetUserByIdRes res) {

        res.code = 0;
        res.data = null;
        res.message = "";
        this.TaroRet(res);

        return 0;
    }

    @Override
    @TarsusMethod
    public int getUserList(@TaroStruct GetUserListReq req, @TaroStruct GetUserListRes res) {
        return 0;
    }

//    public static void main(String[] args) {
//        final TaroInterFace taroInterFace = new TaroInterFaceImpl();
//
//        Class<?>[] interfaces = taroInterFace.getClass().getInterfaces();
//
//        for (Class<?> interfaces2 : interfaces) {
//            TarsusInterFace annotation = interfaces2.getAnnotation(TarsusInterFace.class);
//            System.out.println(annotation.interFace());
//        }
//    }

}

