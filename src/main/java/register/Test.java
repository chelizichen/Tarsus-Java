package register;


import base.ArcBaseClass;
import config.ret;
import decorator.ArcInterFace;
import decorator.ArcMethod;

@ArcInterFace(interFace = "TestInterFace")
public class Test extends ArcBaseClass {
    @ArcMethod
    public ret run(String[] args){
        return ret.success("测试111");
    }
}
