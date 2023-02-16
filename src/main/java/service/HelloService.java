package service;

import decorator.ioc.Collect;
import params.Job;

@Collect
public class HelloService {
    public void sayHello(Job j1){
        System.out.println("cout this jobName >> "+j1.JobName);
    }
}
