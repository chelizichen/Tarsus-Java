package dev_v3_0.category;

import dev_v3_0.stream.T_RStream;
import dev_v3_0.stream.T_WStream;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;

@NoArgsConstructor
public class T_Vector<T extends T_Base> extends ArrayList<T> implements T_Base {
    static String _t_className = "Vector";
    public String _t_value;
    public Boolean isJceStruct;
    public Class<T> VALUE_CLASS;

    public T_Vector(Class<T> T_Value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        super();
        this.VALUE_CLASS = T_Value;
        Field T_Name = T_Value.getDeclaredField("_t_className");
        this._t_value = (String) T_Name.get(null);
        this.isJceStruct = T_Container.JCE_STRUCT.containsKey(this._t_value);
    }

    public boolean push(Object value) {
        try {
            T t = this.VALUE_CLASS.getConstructor().newInstance();
            Method setValue = this.VALUE_CLASS.getDeclaredMethod("SetValue", Object.class);
            setValue.invoke(t, value);
            return this.add(t);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }


    public static <T extends T_Base> T_WStream objToStream(T_Vector<T> target) throws Exception {
        T_WStream ws = new T_WStream();
        int tag = 0;
        for (T_Base t : target) {
            if (target.isJceStruct) {
                T_JceStruct get = T_Container.JCE_STRUCT.get(t.__getClass__().className);
                ws.WriteStruct(tag++, t, get.Write);
            } else {
                ws.WriteAny(tag++, t);
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
                T tBase = (T) rs.ReadStruct(tag++, ReadStream.Base, ReadStream.Read);
                T_Value.add(tBase.GetValue());
            } else {
                T tBase = (T) rs.ReadAny(tag++, T_Value._t_value, null, null);
                T_Value.add(tBase);
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
    public void SetValue(Object value) {

    }

    @Override
    public T_Vector<T> GetValue() {
        return this;
    }
}
