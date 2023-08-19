package com.tarsus.dev_v1_0.service;

import com.tarsus.dev_v1_0.decorator.ioc.Inject;
import com.tarsus.dev_v1_0.decorator.ioc.Service;
import com.tarsus.dev_v1_0.enity.Drug;

@Service
public class HelloService {

    @Inject
    Drug drug;

}


