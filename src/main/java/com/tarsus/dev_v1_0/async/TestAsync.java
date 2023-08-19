package com.tarsus.dev_v1_0.async;

import com.tarsus.dev_v1_0.decorator.async.Async;

public class TestAsync {

    @Async("hello")
    public Object data(Object args){
        System.out.println("args"+111);
        return args;
    }
}
