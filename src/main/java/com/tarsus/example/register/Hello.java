package com.tarsus.example.register;

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

@TarsusInterFace(interFace = "HelloInterFace")
public class Hello extends TarsusBaseInterFace {

    @Inject
    HelloService helloService;


    @TarsusMethod
    public ret TestRet(@TarsusParam Drug d1, @TarsusParam Job j1){
        helloService.sayHello(j1);
        return ret.success(d1);
    }

    @TarsusMethod
    public ret say(String args1,String args2){
        HashMap<String, String> hmp = new HashMap();
        hmp.put("d",args1);
        hmp.put("f",args2);
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
