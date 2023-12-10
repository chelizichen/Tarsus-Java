package dev_v3_0.category;


import dev_v3_0.stream.T_WStream;
import lombok.NoArgsConstructor;

import java.nio.ByteBuffer;
@NoArgsConstructor
public class T_Byte implements T_Base {
    public static String _t_className = "byte";

    private  byte value;

    public T_Byte(byte value) {
        this.value = value;
    }

    @Override
    public T_Class __getClass__() {
        T_Class tc = new T_Class();
        tc.className = T_Byte._t_className;
        tc.valueType = T_Byte._t_className;
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

    @Override
    public String toString() {
        return Byte.toString(this.value);
    }
}
