package dev_v3_0.category;

import lombok.NoArgsConstructor;

import java.util.HashMap;

@NoArgsConstructor
public class T_Map<T extends T_Base> extends HashMap<String, T> implements T_Base {
    public static String _t_className = "Map";
    public String _t_value;
    public Boolean isJceStruct;

    public T_Map(T T_Value) {
        super();
        this._t_value = T_Value.__getClass__().className;
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
}
