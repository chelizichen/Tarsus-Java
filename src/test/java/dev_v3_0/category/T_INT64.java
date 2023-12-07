package dev_v3_0.category;


import dev_v3_0.stream.T_WStream;

public class T_INT64 implements T_Base {
    public static String _t_className = "int64";

    private final int value;

    public T_INT64(int value) {
        this.value = value;
    }

    @Override
    public T_WStream ObjectToStream() throws Exception {
        return null;
    }

    @Override
    public <K> K StreamToObject() {
        return null;
    }

    @Override
    public T_Class __getClass__() {
        T_Class tc = new T_Class();
        tc.className = T_INT64._t_className;
        tc.valueType = T_INT64._t_className;
        return tc;
    }

    @Override
    public Object GetValue() {
        return this.value;
    }
}
