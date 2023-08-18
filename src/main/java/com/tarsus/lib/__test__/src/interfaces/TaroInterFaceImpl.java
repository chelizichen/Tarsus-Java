package com.tarsus.lib.__test__.src.interfaces;


import com.tarsus.example.struct.*;
import com.tarsus.lib.decorator.ms.TarsusInterFace;
import com.tarsus.lib.decorator.ms.TarsusMethod;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@TarsusInterFace("TaroInterFaceTest")
public class TaroInterFaceImpl  {
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
        return 0;
    }

    @TarsusMethod
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
        res.data = userStream.collect(Collectors.toList());
        res.code=0;
        res.message="111";
        return res;
    }
}
