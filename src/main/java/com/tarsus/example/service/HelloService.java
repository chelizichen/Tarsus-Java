package com.tarsus.example.service;

import com.tarsus.example.decorator.ioc.Inject;
import com.tarsus.example.decorator.ioc.Service;
import com.tarsus.example.enity.Drug;
import com.tarsus.example.enity.Job;

@Service
public class HelloService {

    @Inject
    Drug drug;

    public void sayHello(Job j1){
        System.out.println("cout this jobName >> "+j1.JobName);
    }
}


