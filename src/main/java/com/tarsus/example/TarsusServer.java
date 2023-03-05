package com.tarsus.example;


import com.tarsus.example.async.TestAsync;
import com.tarsus.example.base.Tarsus;
import com.tarsus.example.decorator.TarsusMsApplication;
import com.tarsus.example.register.Hello;
import com.tarsus.example.register.Test;

//
//@TarsusMsApplication
//public class TarsusServer extends Tarsus {
//    public static void main(String[] args) {
//        TarsusServer c = new TarsusServer();
//        c.loadInterFace(new Class[]{Hello.class, Test.class});
//        c.loadEvents(new Class[]{TestAsync.class});
//        c.boost(TarsusServer.class);
//    }
//}

// 使用 SpringBoot  的方式启动服务
@TarsusMsApplication
public class TarsusServer{
    public static void main(String[] args) {
       Tarsus.run(com.tarsus.example.TarsusServer.class,args);
    }
}

