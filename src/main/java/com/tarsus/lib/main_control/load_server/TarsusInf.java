package com.tarsus.lib.main_control.load_server;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface Tarsus$Inf {
    public void LoadInterFace(Class<?>[] interfaces) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
    public void LoadStruct();
    public void LoadServer() throws InvocationTargetException, IllegalAccessException;
}
