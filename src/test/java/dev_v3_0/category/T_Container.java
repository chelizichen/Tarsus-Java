package dev_v3_0.category;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

public class T_Container {
    public static final HashMap<String,T_JceStruct> JCE_STRUCT = new HashMap<>();
    public static <T>Class<T> getDeclareProtoClass(Class<?> clazz,String className) throws Exception {
        Class<T> target = null;
        Class<?>[] dcs = clazz.getDeclaredClasses();
        for (Class<?> dc : dcs) {
            if (dc.getSimpleName().equals(className)) {
                target =  (Class<T>) dc;
                break;
            }
        }
        if(target == null) {
            throw new Exception("ClassNotFoundError");
        }
        return target;
    }


}
