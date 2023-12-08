package dev_v3_0.category;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

public class T_Container {
    public static final HashMap<String,T_JceStruct> JCE_STRUCT = new HashMap<>();
    public static void Set(String className,T_JceStruct jceStruct){
        T_Container.JCE_STRUCT.put(className,jceStruct);
    }
    public static Optional<T_JceStruct> Get(String className){
        T_JceStruct jceStruct = T_Container.JCE_STRUCT.get(className);
        return Optional.ofNullable(jceStruct);
    }
}
