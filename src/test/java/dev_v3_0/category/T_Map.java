package dev_v3_0.category;

import dev_v3_0.stream.T_WStream;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class T_Map<T extends T_Base> extends HashMap<String,T> implements T_Base {
    static String _t_className = "Map";
    public String _t_value;
    public Boolean isJceStruct;
    public T_Map(T T_Value){
        super();
        this._t_value = T_Value.__getClass__().className;
        this.isJceStruct = T_Container.Value.containsKey(this._t_value);
    }

    @Override
    public T_WStream ObjectToStream() throws Exception {
        T_WStream ws = new T_WStream();
        return ws;
    }

    @Override
    public T_Map<T> StreamToObject(ByteBuffer buf, T_Base T_Value, Integer ByteLength) {
        return null;
    }

    @Override
    public T_Class __getClass__() {
        T_Class tClass = new T_Class();
        tClass.className = T_Map._t_className;
        tClass.valueType = this._t_value;
        return tClass;
    }

    @Override
    public Object GetValue() {
        return this;
    }
}
