package com.tarsus.lib.main_control.load_server;

import java.lang.reflect.InvocationTargetException;

public interface TarsusInf {
    public void LoadInterFace(Class<?>[] interfaces) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
    public void LoadStruct();
    public void LoadServer() throws InvocationTargetException, IllegalAccessException;
}
