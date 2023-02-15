package register;

import base.ArcBaseClass;
import config.ret;
import decorator.ArcMethod;
import decorator.ArcParams;
import decorator.ArcInterFace;
import dto.Job;
import dto.Person;

import java.util.HashMap;

@ArcInterFace(interFace = "HelloInterFace")
public class Hello extends ArcBaseClass {

    @ArcMethod
    public ret hello(String[] args){
        System.out.println(args);
        for (String s:args){
            System.out.println(s);
        }
        ret success = ret.success(args);
        return success;
    }

    @ArcMethod
    public ret TestRet(@ArcParams("Person") Person p1, @ArcParams("Job")Job j1){
        System.out.println("函数内打印"+p1.sex);
        System.out.println("函数内打印"+p1.name);
        System.out.println("函数内打印"+p1.address);
        System.out.println("函数内打印"+p1.age);
        System.out.println("函数内打印"+j1.JobName);
        return ret.success("1111");
    }

    @ArcMethod
    public ret say(String[] args){

        HashMap<String, String> hmp = new HashMap();
        hmp.put("a","1");
        hmp.put("b","2");
        hmp.put("c","3");
        ret success = ret.success(hmp);
        return success;
    }
}
