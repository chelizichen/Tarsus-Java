package dev_v3_0.category;

import dev_v3_0.stream.T_RStream;
import dev_v3_0.stream.T_WStream;

import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class T_Vector<T extends T_Base> extends ArrayList<T> implements T_Base {
    static String _t_className = "Vector";
    public String _t_value;
    public Boolean isJceStruct;

    public T_Vector(Class<T> T_Value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        super();
        T_Class tc = (T_Class) T_Value.getDeclaredMethod("__getClass__").invoke(T_Value);
        this._t_value = tc.className;
        this.isJceStruct = T_Container.JCE_STRUCT.containsKey(this._t_value);
    }


    public static <T extends T_Base> T_WStream objToStream(T_Vector<T> target) throws Exception {
        T_WStream ws = new T_WStream();
        for (T_Base t : target) {
            if (target.isJceStruct) {
                T_JceStruct get = T_Container.JCE_STRUCT.get(t.__getClass__().className);
                ws.WriteStruct(ws.tag, t, get.Write);
            } else {
                ws.WriteAny(ws.tag, t);
            }
        }
        return ws;
    }

    public static <T extends T_Base> T_Vector<T> streamToObj(ByteBuffer buffer, T_Vector<T> T_Value, Integer ByteLength) throws Exception {
        T_RStream rs = new T_RStream(buffer);
        int tag = 0;
        do {
            if (T_Value.isJceStruct) {
                T_JceStruct ReadStream = T_Container.JCE_STRUCT.get(T_Value._t_value);
                T_Base tBase = rs.ReadStruct(tag++, ReadStream.Base, ReadStream.Read);
                T_Value.add(tBase.GetValue());
            } else {
                T_Base tBase = rs.ReadAny(tag++, T_Value._t_value, null, null);
                T_Value.add(tBase.GetValue());
            }
        } while (rs.position < ByteLength);
        return T_Value;
    }


    @Override
    public T_Class __getClass__() {
        T_Class tClass = new T_Class();
        tClass.className = T_Vector._t_className;
        tClass.valueType = this._t_value;
        return tClass;
    }

    @Override
    public T_Vector<T> GetValue() {
        return this;
    }
}
