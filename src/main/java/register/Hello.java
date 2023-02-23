package register;

import base.TarsusBaseInterFace;
import base.TarsusEvents;
import config.ret;
import decorator.TarsusMethod;
import decorator.TarsusParam;
import decorator.TarsusInterFace;
import decorator.ioc.Inject;
import enity.Drug;
import enity.Job;
import service.HelloService;

import java.util.HashMap;

@TarsusInterFace(interFace = "HelloInterFace")
public class Hello extends TarsusBaseInterFace {

    @Inject
    HelloService helloService;


    @TarsusMethod
    public ret TestRet(@TarsusParam Drug d1, @TarsusParam Job j1){
        helloService.sayHello(j1);
        return ret.success(d1);
    }

    @TarsusMethod
    public ret say(String args1,String args2){
        HashMap<String, String> hmp = new HashMap();
        hmp.put("d",args1);
        hmp.put("f",args2);
        ret success = ret.success(hmp);
        return success;
    }

    @TarsusMethod
    public ret TestAsync(String args1,String args2){
        HashMap<String, String> hmp = new HashMap();
        hmp.put("d",args1);
        hmp.put("f",args2);
        TarsusEvents.emit("hello","111");
        System.out.println("被执行");
        ret success = ret.success(hmp);
        return success;
    }
}
