package register;


import base.ArcInterFace;
import config.ret;
import decorator.ProxyInterFace;

@ProxyInterFace(interFace = "TestInterFace")
public class Test extends ArcInterFace {
    public ret run(String[] args){
        return ret.success("测试111");
    }
}
