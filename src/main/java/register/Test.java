package register;


import base.TarsusBaseInterFace;
import config.ret;
import decorator.TarsusInterFace;
import decorator.TarsusMethod;
import decorator.ioc.Inject;
import service.HelloService;

@TarsusInterFace(interFace = "TestInterFace")
public class Test extends TarsusBaseInterFace {

    @Inject
    HelloService helloService;

    @TarsusMethod
    public ret run(String[] args){
        return ret.success("测试111");
    }
}
