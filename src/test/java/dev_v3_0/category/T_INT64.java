package dev_v3_0.category;


import dev_v3_0.stream.T_WStream;

import java.nio.ByteBuffer;

public class T_INT64 implements T_Base {
    public static String _t_className = "int64";

    private final long value;

    public T_INT64(long value) {
        this.value = value;
    }

    @Override
    public T_Class __getClass__() {
        T_Class tc = new T_Class();
        tc.className = T_INT64._t_className;
        tc.valueType = T_INT64._t_className;
        return tc;
    }

    @Override
    public Long GetValue() {
        return this.value;
    }
}
