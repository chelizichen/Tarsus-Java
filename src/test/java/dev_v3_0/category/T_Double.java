package dev_v3_0.category;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class T_Double implements T_Base {
    public static String _t_className = "double";

    private double value;

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
    public void SetValue(Object value) {
        this.value = (double) value;
    }

    @Override
    public Double GetValue() {
        return this.value;
    }
}
