package dev_v3_0.category;

import dev_v3_0.stream.T_WStream;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Optional;

public class T_Vector<T extends T_Base> extends ArrayList<T> implements T_Base {
    static String _t_className = "Vector";
    public String _t_value;
    public Boolean isJceStruct;

    public T_Vector(T T_Value) {
        super();
        this._t_value = T_Value.__getClass__().className;
        this.isJceStruct = T_Container.JCE_STRUCT.containsKey(this._t_value);
    }


    @Override
    public T_WStream ObjectToStream() throws Exception {
        T_WStream ws = new T_WStream();
        for (T t : this) {
            if (this.isJceStruct) {
                Optional<T_JceStruct> get = T_Container.Get(this._t_value);
                if (!get.isPresent()) throw new Exception("StructNotFoundError:" + this._t_value);
                T_JceStruct jceStruct = get.get();
                ws.WriteStruct(ws.tag, t, jceStruct.Write);
            } else {
                ws.WriteAny(ws.tag, t);
            }
        }
        return ws;
    }

    @Override
    public T_Vector<T> StreamToObject(ByteBuffer buf, T_Base T_Value, Integer ByteLength) {
        return null;
    }

    @Override
    public T_Class __getClass__() {
        T_Class tClass = new T_Class();
        tClass.className = T_Vector._t_className;
        tClass.valueType = this._t_value;
        return tClass;
    }

    @Override
    public Object GetValue() {
        return this;
    }
}
