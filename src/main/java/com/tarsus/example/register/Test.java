package com.tarsus.example.register;


import com.tarsus.example.base.TarsusBaseInterFace;
import com.tarsus.example.config.ret;
import com.tarsus.example.decorator.TarsusInterFace;
import com.tarsus.example.decorator.TarsusMethod;
import com.tarsus.example.decorator.ioc.Inject;
import com.tarsus.example.service.HelloService;

@TarsusInterFace(interFace = "TestInterFace")
public class Test extends TarsusBaseInterFace {

    @Inject
    HelloService helloService;

    @TarsusMethod
    public ret run(String[] args){
        return ret.success("测试111");
    }
}
