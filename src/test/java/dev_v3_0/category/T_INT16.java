package dev_v3_0.category;


import dev_v3_0.stream.T_WStream;

import java.nio.ByteBuffer;

public class T_INT16 implements T_Base {
    public static String _t_className = "int16";

    private short value;

    public T_INT16(short value) {
        this.value = value;
    }

    @Override
    public T_Class __getClass__() {
        T_Class tc = new T_Class();
        tc.className = T_INT16._t_className;
        tc.valueType = T_INT16._t_className;
        return tc;
    }

    @Override
    public void SetValue(Object value) {
        this.value = (short) value;
    }

    @Override
    public Short GetValue() {
        return this.value;
    }
}
