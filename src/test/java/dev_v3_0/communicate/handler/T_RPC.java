package dev_v3_0.communicate.handler;

import dev_v3_0.category.*;

import java.lang.reflect.Method;
import java.util.HashMap;

public class T_RPC {
    public static enum Handlers {
        Req,
        Res
    }

    public static HashMap<String, HashMap<Handlers, T_JceStruct>> METHODS = new HashMap<String, HashMap<Handlers, T_JceStruct>>();

    public static class T_Context {
        public T_INT32 ByteLength;
        public T_String ModuleName;
        public T_String InvokeMethod;
        public T_String InvokeRequest;
        public T_String InvokeResponse;
        public T_Vector<T_String> TraceId;

        public T_Context(T_INT32 byteLength, T_String moduleName, T_String invokeMethod, T_String invokeRequest, T_String invokeResponse, T_Vector<T_String> traceId) {
            ByteLength = byteLength;
            ModuleName = moduleName;
            InvokeMethod = invokeMethod;
            InvokeRequest = invokeRequest;
            TraceId = traceId;
        }
    }

    public static HashMap<String, Object> Modules = new HashMap<String, Object>();

    public static void SetModule(String ModuleName, Object module) {
        T_RPC.Modules.put(ModuleName, module);
    }

    public static Object GetModule(String Module) {
        return T_RPC.Modules.get(Module);
    }

    public static Method GetModuleMethod(String Module,String MethodName) throws NoSuchMethodException {
        Object INSTANCE = T_RPC.GetModule(Module);
        return INSTANCE.getClass().getDeclaredMethod(MethodName, T_Context.class, T_Base.class);
    }
}
