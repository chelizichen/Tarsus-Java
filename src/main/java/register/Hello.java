package register;

import base.ArcBaseClass;
import config.ret;
import decorator.ArcMethod;
import decorator.ArcParams;
import decorator.ArcInterFace;
import decorator.ioc.Inject;
import enity.Drug;
import enity.Job;
import service.HelloService;

import java.util.HashMap;

@ArcInterFace(interFace = "HelloInterFace")
public class Hello extends ArcBaseClass {

    @Inject
    HelloService helloService;


    @ArcMethod
    public ret TestRet(@ArcParams Drug d1, @ArcParams Job j1){
        helloService.sayHello(j1);
        return ret.success(d1);
    }

    @ArcMethod
    public ret say(String args1,String args2){
        HashMap<String, String> hmp = new HashMap();
        hmp.put("d",args1);
        hmp.put("f",args2);
        ret success = ret.success(hmp);
        return success;
    }
}
