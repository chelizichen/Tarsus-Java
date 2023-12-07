package dev_v3_0.category;


import dev_v3_0.stream.T_WStream;

import java.nio.ByteBuffer;

public class T_String implements T_Base {
    public static String _t_className = "string";

    private final String value;

    public T_String(String value) {
        this.value = value;
    }

    @Override
    public T_WStream ObjectToStream() throws Exception {
        return null;
    }

    @Override
    public T_Base StreamToObject(ByteBuffer buf, T_Base T_Value, Integer ByteLength) {
        return null;
    }


    @Override
    public T_Class __getClass__() {
        T_Class tc = new T_Class();
        tc.className = T_String._t_className;
        tc.valueType = T_String._t_className;
        return tc;
    }

    @Override
    public String GetValue() {
        return this.value;
    }

}
