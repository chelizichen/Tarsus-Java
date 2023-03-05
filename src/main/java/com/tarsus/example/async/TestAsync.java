package com.tarsus.example.async;

import com.tarsus.example.decorator.async.Async;

public class TestAsync {

    @Async("hello")
    public Object data(Object args){
        System.out.println("args"+111);
        return args;
    }
}
