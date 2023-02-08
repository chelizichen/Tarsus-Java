package register;

import base.ArcInterFace;
import com.alibaba.fastjson2.JSONObject;
import config.ret;
import decorator.ProxyInterFace;

import java.util.HashMap;

@ProxyInterFace(interFace = "HelloInterFace")
public class Hello extends ArcInterFace {

    public ret hello(String[] args){
        System.out.println(args);
        for (String s:args){
            System.out.println(s);
        }
        final Object o = JSONObject.toJSONString(args);
        ret success = ret.success(o);
        return success;
    }

    public ret say(String[] args){

        HashMap<String, String> hmp = new HashMap();
        hmp.put("a","1");
        hmp.put("b","2");
        hmp.put("c","3");
        ret success = ret.success(hmp);
        return success;
    }
}
