package com.tarsus.example.service;

import com.tarsus.example.decorator.ioc.Inject;
import com.tarsus.example.decorator.ioc.Service;
import com.tarsus.example.enity.Drug;

@Service
public class HelloService {

    @Inject
    Drug drug;

}


