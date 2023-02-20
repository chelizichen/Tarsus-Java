package service;

import decorator.ioc.Inject;
import decorator.ioc.Service;
import enity.Drug;
import enity.Job;

@Service
public class HelloService {

    @Inject
    Drug drug;

    public void sayHello(Job j1){
        System.out.println("cout this jobName >> "+j1.JobName);
    }
}


