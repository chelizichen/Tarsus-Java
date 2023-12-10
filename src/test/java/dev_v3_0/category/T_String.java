package dev_v3_0.category;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class T_String implements T_Base {
    public static String _t_className = "string";

    private String value;

    public T_String(String value) {
        this.value = value;
    }


    @Override
    public T_Class __getClass__() {
        T_Class tc = new T_Class();
        tc.className = T_String._t_className;
        tc.valueType = T_String._t_className;
        return tc;
    }

    @Override
    public void SetValue(Object value) {
        this.value = (String) value;
    }

    @Override
    public String GetValue() {
        return this.value;
    }

}
