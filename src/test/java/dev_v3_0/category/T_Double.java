package dev_v3_0.category;


import dev_v3_0.stream.T_WStream;

import java.nio.ByteBuffer;

public class T_Double implements T_Base {
    public static String _t_className = "double";

    private final double value;

    public T_Double(double value) {
        this.value = value;
    }

    @Override
    public T_Class __getClass__() {
        T_Class tc = new T_Class();
        tc.className = T_Double._t_className;
        tc.valueType = T_Double._t_className;
        return tc;
    }

    @Override
    public Double GetValue() {
        return this.value;
    }
}
