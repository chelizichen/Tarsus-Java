package com.tarsus.dev_v1_0.register;

import com.tarsus.dev_v1_0.base.TarsusBaseInterFace;
import com.tarsus.dev_v1_0.decorator.TarsusInterFace;
import com.tarsus.dev_v1_0.decorator.TarsusMethod;
import com.tarsus.dev_v1_0.struct.GetUserByIdReq;
import com.tarsus.dev_v1_0.struct.GetUserByIdRes;
import com.tarsus.dev_v1_0.struct.GetUserListRes;
import com.tarsus.dev_v1_0.struct.GetUserListReq;
import com.tarsus.dev_v1_0.struct.User;
import com.tarsus.dev_v1_0.taro.TaroInterFace;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@TarsusInterFace("TaroInterFaceTest")
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
        res.code = req.id;
        res.message = req.basic.token;
        this.json(res);
        return 0;
    }

    @Override
    @TarsusMethod
    public int getUserList(GetUserListReq req, GetUserListRes res) {

        final Stream<User> userStream = req.ids.stream().map(integer -> {
            final User user = new User();
            user.id = integer.toString();
            user.name = "name" + integer.toString();
            user.address = "address" + integer.toString();
            user.age = "age" + integer.toString();
            user.fullName = "fullName" + integer.toString();
            return user;
        });
        res.data = userStream.collect(Collectors.toList());
        res.code=0;
        res.message="111";

        this.json(res);

        return 0;
    }
}
