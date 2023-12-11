package dev_v3_0.category;

import dev_v3_0.stream.T_RStream;
import dev_v3_0.stream.T_WStream;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Set;

@NoArgsConstructor
public class T_Map<T extends T_Base> extends HashMap<String, T> implements T_Base {
    public static String _t_className = "Map";
    public String _t_value;
    public Boolean isJceStruct;
    public Class<T> VALUE_TYPE;

    public T_Map(Class<T> T_Value) throws NoSuchFieldException, IllegalAccessException {
        super();
        Field T_Name = T_Value.getDeclaredField("_t_className");
        this._t_value = (String) T_Name.get(null);
        this.VALUE_TYPE = T_Value;
        this.isJceStruct = T_Container.JCE_STRUCT.containsKey(this._t_value);
    }

    @Override
    public T_Class __getClass__() {
        T_Class tClass = new T_Class();
        tClass.className = T_Map._t_className;
        tClass.valueType = this._t_value;
        return tClass;
    }

    @Override
    public void SetValue(Object value) {
        ((HashMap) value).putAll(this);
    }

    @Override
    public T_Map<T> GetValue() {
        return this;
    }

    public static <T extends T_Base> T_WStream objToStream(T_Map<T> T_MapVal) throws Exception {
        T_WStream ws = new T_WStream();
        int tag = 0;
        Set<String> keys = T_MapVal.keySet();
        for (String key : keys) {
            T value = T_MapVal.get(key);
            if (T_MapVal.isJceStruct) {
                ws.WriteString(tag++, key);
                ws.WriteStruct(tag++, value, T_Container.getDeclareProtoClass(T_MapVal.VALUE_TYPE, "write"));
            }
        }
        return ws;
    }

    public static <T extends T_Base> T_Map<T> streamToObj(ByteBuffer buffer, T_Map<T> TMap, Integer ByteLength) throws Exception {
        T_RStream rs = new T_RStream(buffer);
        int tag = 0;
        do {
            T_String key = rs.ReadString(tag++);
            if (TMap.isJceStruct) {
                T value = rs.ReadStruct(tag++, TMap.VALUE_TYPE, T_Container.getDeclareProtoClass(TMap.VALUE_TYPE, "read"));
                TMap.put(key.GetValue(), value);
            } else {
                T_Base value = rs.ReadAny(tag++, TMap._t_value, null, null);
                TMap.put(key.GetValue(), value.GetValue());
            }
        } while (rs.position < ByteLength);
        return TMap;
    }
}
