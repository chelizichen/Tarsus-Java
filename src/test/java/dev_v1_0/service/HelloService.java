package dev_v1_0.service;

import dev_v1_0.decorator.ioc.Inject;
import dev_v1_0.decorator.ioc.Service;
import dev_v1_0.enity.Drug;

@Service
public class HelloService {

    @Inject
    Drug drug;

}


