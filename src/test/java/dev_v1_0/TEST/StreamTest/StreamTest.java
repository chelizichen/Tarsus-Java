package dev_v1_0.TEST.StreamTest;

import com.alibaba.fastjson.JSON;
import dev_v1_0.base.TarsusStream;

import java.util.List;

public class StreamTest {
    public static void main(String[] args) {
        TarsusStream.SetClass(GetUserByIdRes.class);
        TarsusStream.SetClass(User.class);
        TestListStruct();
    }

    public static void TestListStruct(){
        String stf = "[\"0\",\"ok\",[\"1\",\"name\",\"11\",\"fullName\",\"address\"],[[\"1\",\"name\",\"11\",\"fullName\",\"address\"],[\"1\",\"name\",\"11\",\"fullName\",\"address\"],[\"1\",\"name\",\"11\",\"fullName\",\"address\"],[\"1\",\"name\",\"11\",\"fullName\",\"address\"],[\"1\",\"name\",\"11\",\"fullName\",\"address\"]]]";
        final List jsonArray = JSON.parseArray(stf);

        final GetUserByIdRes getUserByIdRes = new GetUserByIdRes(jsonArray);
        System.out.println(jsonArray);
        System.out.println(getUserByIdRes.json());
        System.out.println(getUserByIdRes.users.get(0).json());

    }
}
