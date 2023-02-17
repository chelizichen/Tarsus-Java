package service;

import decorator.ioc.Collect;
import decorator.ioc.Inject;
import enity.Drug;
import enity.Job;

@Collect
public class HelloService {
    @Inject
    Drug drug;

    public void sayHello(Job j1){
        System.out.println("cout this jobName >> "+j1.JobName);
    }
}


