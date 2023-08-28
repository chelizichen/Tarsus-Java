package com.tarsus.dev_v2_0.interfaces;


import com.tarsus.dev_v2_0.struct.*;
import com.tarsus.dev_v2_0.taro.TaroInterFace;
import com.tarsus.lib.lib_decorator.ioc.Inject;
import com.tarsus.lib.lib_decorator.ms.TarsusInterFace;
import com.tarsus.lib.lib_decorator.ms.TarsusMethod;
import com.tarsus.lib.main_control.load_server.impl.Tarsus;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@TarsusInterFace("TaroInterFaceTest")
public class TaroInterFaceImpl implements TaroInterFace {
    @Inject
    public CacheImpl cache;
    @TarsusMethod
    @Override
    public GetUserByIdRes getUserById(GetUserByIdReq req, GetUserByIdRes res) {
        res.data = new User();
        res.data.address = "1";
        res.data.age = "1";
        res.data.fullName = "1";
        res.data.id = req.basic.token;
        res.data.name = "1";
        res.code = req.id;
        res.message = req.basic.token;
        return res;
    }

    @TarsusMethod
    @Override
    public GetUserListRes getUserList(GetUserListReq req, GetUserListRes res) {
        final Stream<User> userStream = req.ids.stream().map(integer -> {
            final User user = new User();
            user.id = integer.toString();
            user.name = "name" + integer.toString();
            user.address = "address" + integer.toString();
            user.age = "age" + integer.toString();
            user.fullName = "fullName" + integer.toString();
            return user;
        });
        cache.ProxySendRequest("setCache",new SetCacheReq(),new SetCacheRes(),data->{
            // 异步数据拿到以后 对异步数据进行处理
            data.code = "2";
            data.message = "ok";
            // 由于是异步函数，此时函数已经执行完毕了
            Tarsus.asyncObserver.emit(req.__eid__,data);
            return data;
        });
        res.data = userStream.collect(Collectors.toList());
        res.code = 0;
        res.message = "111";
        return null;
    }
}
