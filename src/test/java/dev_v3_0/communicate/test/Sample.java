package dev_v3_0.communicate.test;

import dev_v3_0.category.T_INT32;
import dev_v3_0.category.T_INT8;
import dev_v3_0.category.T_String;
import dev_v3_0.communicate.handler.T_RPC;
import dev_v3_0.test.QueryId;
import dev_v3_0.test.User;

public class Sample {
    public User getUserById(T_RPC.T_Context ctx, QueryId req){
        User user = new User();
        user.userId = new T_INT32(1);
        user.userAddress = new T_String("wuhan");
        user.userName = new T_String("cheng");
        user.phoneNumber = new T_String("13478812281");
        user.createTime = new T_String("2023-12-08");
        user.status = new T_INT8(1);
        return user;
    }
}
