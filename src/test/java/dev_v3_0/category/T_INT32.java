package dev_v3_0.category;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class T_INT32 implements T_Base {
    public static String _t_className = "int32";

    private int value;

    public T_INT32(int value) {
        this.value = value;
    }

    @Override
    public T_Class __getClass__() {
        T_Class tc = new T_Class();
        tc.className = T_INT32._t_className;
        tc.valueType = T_INT32._t_className;
        return tc;
    }

    @Override
    public void SetValue(Object value) {
        this.value = (int) value;
    }

    @Override
    public Integer GetValue() {
        return this.value;
    }
}
