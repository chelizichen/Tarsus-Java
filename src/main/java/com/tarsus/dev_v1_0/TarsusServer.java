package com.tarsus.dev_v1_0;


import com.tarsus.dev_v1_0.base.Tarsus;
import com.tarsus.dev_v1_0.decorator.TarsusMsApplication;

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
       Tarsus.run(TarsusServer.class,args);
    }
}

