package dev_v3_0.category;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class T_INT8 implements T_Base {
    public static String _t_className = "int8";

    private byte value;

    public T_INT8(int value) {
        this.value = (byte) value;
    }

    @Override
    public T_Class __getClass__() {
        T_Class tc = new T_Class();
        tc.className = T_INT8._t_className;
        tc.valueType = T_INT8._t_className;
        return tc;
    }

    @Override
    public void SetValue(Object value) {
        this.value = (byte) value;
    }

    @Override
    public Byte GetValue() {
        return this.value;
    }
}
