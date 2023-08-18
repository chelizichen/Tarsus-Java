package com.tarsus.lib.__test__;

import com.tarsus.lib.__test__.src.interfaces.TaroInterFaceImpl;
import com.tarsus.lib.decorator.ms.TarsusMsApplication;
import com.tarsus.lib.main_control.load_server.impl.Tarsus;

import java.lang.reflect.InvocationTargetException;

@TarsusMsApplication
public class TarsusJavaServer {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Tarsus tarsus = new Tarsus();
        tarsus.LoadInterFace(new Class[]{TaroInterFaceImpl.class});
//        tarsus.LoadServer();
    }
}
