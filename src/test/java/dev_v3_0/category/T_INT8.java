package dev_v3_0.category;


import dev_v3_0.stream.T_WStream;

import java.nio.ByteBuffer;

public class T_INT8 implements T_Base {
    public static String _t_className = "int8";

    private final byte value;

    public T_INT8(int value) {
        this.value = (byte) value;
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
        tc.className = T_INT8._t_className;
        tc.valueType = T_INT8._t_className;
        return tc;
    }

    @Override
    public Byte GetValue() {
        return this.value;
    }
}
