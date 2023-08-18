package com.tarsus.lib.main_control.load_server;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface TarsusStreamInf {
    public String read_string(Integer index);
    public Integer read_int(Integer index);
    public <T>List<T> read_list(Integer index,String className) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
    public <T>T read_struct(Integer index,String className);
    public <T>T read(Object value,String type);
}
