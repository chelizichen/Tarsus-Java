package com.tarsus.dev_v2_0;

import com.tarsus.dev_v2_0.interfaces.*;
import com.tarsus.lib.lib_decorator.ms.TarsusMsApplication;
import com.tarsus.lib.main_control.load_server.impl.Tarsus;

import java.lang.reflect.InvocationTargetException;

@TarsusMsApplication
public class TarsusJavaServer {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Tarsus tarsus = new Tarsus(TarsusJavaServer.class);
        tarsus.LoadInterFace(new Class[]{
                PlanImpl.class,
                CacheImpl.class,
                RecordImpl.class,
                ShareImpl.class,
                UserImpl.class,
                WordImpl.class
        });
        tarsus.LoadStruct();
        tarsus.LoadServer();
    }
}
