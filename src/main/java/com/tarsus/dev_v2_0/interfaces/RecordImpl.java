package com.tarsus.dev_v2_0.interfaces;

import com.tarsus.dev_v2_0.struct.*;
import com.tarsus.dev_v2_0.taro.RecordInf;
import com.tarsus.lib.lib_decorator.ms.TarsusInterFace;
import com.tarsus.lib.lib_decorator.ms.TarsusMethod;
import com.tarsus.lib.main_control.load_server.impl.Tarsus;

import java.util.ArrayList;

@TarsusInterFace("record")
public class RecordImpl implements RecordInf {

    UserImpl userServer = new UserImpl();

    @Override
    @TarsusMethod
    public getUserRecordRes getUserRecord(queryIdReq req, getUserRecordRes res) {
        res.user_id = 1;
        res.data = new ArrayList<Record>();
        res.message = "ok";
        res.total = 10;
        return res;
    }

    @Override
    @TarsusMethod
    public baseRes addUserRecord(queryIdReq req, baseRes res) {
        return null;
    }

    @Override
    @TarsusMethod
    public baseRes delUserRecord(queryIdReq req, baseRes res) {
        queryIdReq idsReq = new queryIdReq();
        idsReq.id = req.id;
        getUserByIdRes queryUsersNameRes = new getUserByIdRes();
        res.code = 0;
        res.message = "1";
        userServer.ProxySendRequest("getUserById",idsReq,queryUsersNameRes,data->{
            System.out.println("再执行异步方法");
            System.out.println(data.data);
            System.out.println(data.code);
            System.out.println(data.message);
            res.code = data.code;
            res.message = data.message;
            Tarsus.asyncObserver.emit(req.__eid__,res);
            return null;
        });
        System.out.println("先执行同步方法");
        return null;
    }

    @Override
    @TarsusMethod
    public baseRes setRecord(setRecordReq req, baseRes res) {
        return null;
    }

    @Override
    @TarsusMethod
    public getCurrRecordLenRes getCurrRecordLen(queryIdReq req, getCurrRecordLenRes res) {
        return null;
    }
}
