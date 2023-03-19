package com.tarsus.example.register;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.tarsus.example.base.TarsusBaseInterFace;
import com.tarsus.example.base.TarsusEvents;
import com.tarsus.example.config.ret;
import com.tarsus.example.decorator.TarsusMethod;
import com.tarsus.example.decorator.TarsusParam;
import com.tarsus.example.decorator.TarsusInterFace;
import com.tarsus.example.decorator.ioc.Inject;
import com.tarsus.example.enity.Drug;
import com.tarsus.example.enity.Job;
import com.tarsus.example.service.HelloService;

import java.util.HashMap;
import java.util.List;

@TarsusInterFace(interFace = "HelloInterFace")
public class Hello extends TarsusBaseInterFace {

    @Inject
    HelloService helloService;


    @TarsusMethod
    public ret TestRet(@TarsusParam Drug d1, @TarsusParam Job j1){
        helloService.sayHello(j1);
        return ret.success(d1);
    }

    // 测试 List
    @TarsusMethod
    public ret say(String args1, String args2, String args3){
        HashMap<String, String> hmp = new HashMap();
        hmp.put("d",args1);
        hmp.put("f",args2);
        List jsonObject = JSON.parseArray(args3,String.class);
        System.out.println(jsonObject.get(1));
        ret success = ret.success(hmp);
        return success;
    }

    @TarsusMethod
    public ret TestAsync(String args1,String args2){
        HashMap<String, String> hmp = new HashMap();
        hmp.put("d",args1);
        hmp.put("f",args2);
        TarsusEvents.emit("hello","111");
        System.out.println("被执行");
        ret success = ret.success(hmp);
        return success;
    }
}
