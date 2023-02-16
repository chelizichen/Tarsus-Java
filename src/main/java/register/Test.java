package register;


import base.ArcBaseClass;
import config.ret;
import decorator.ArcInterFace;
import decorator.ArcMethod;
import decorator.ioc.Inject;
import service.HelloService;

@ArcInterFace(interFace = "TestInterFace")
public class Test extends ArcBaseClass {

    @Inject
    HelloService helloService;

    @ArcMethod
    public ret run(String[] args){
        return ret.success("测试111");
    }
}
