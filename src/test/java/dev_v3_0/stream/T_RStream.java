package dev_v3_0.stream;

import dev_v3_0.category.*;
import io.reactivex.rxjava3.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class T_RStream {
    public ByteBuffer originBuf;
    public Integer position = 0;

    public HashMap<Integer, String> Tag2Field = new HashMap<>();
    public HashMap<String, T_Base> readStreamToObj = new HashMap<String, T_Base>();

    public T_RStream(ByteBuffer originBuf) {
        this.originBuf = originBuf;
    }

    ByteBuffer createBuffer(Integer size) {
        return ByteBuffer.allocate(size);
    }

    public <T> T toObj(Class<T> tClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T_Map<T_Base> tb = new T_Map<>();
        tb.putAll(readStreamToObj);
        this.readStreamToObj.putAll(tb);
        return tClass.getDeclaredConstructor(T_Map.class).newInstance(tb);
    }

    public <T> T toObj(Class<T> tClass, HashMap<String, T_Base> readStreamToObj) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T_Map<T_Base> tb = new T_Map<>();
        tb.putAll(readStreamToObj);
        return tClass.getDeclaredConstructor(T_Map.class).newInstance(tb);
    }

    public <T extends T_Base> T PutTagValToMap(Integer tag, T value) {
        String field = this.Tag2Field.get(tag);
        this.readStreamToObj.put(field, value);
        return value;
    }

    public <V>T_Base ReadAny(Integer tag, T_Base type, @Nullable Class<V> T_KEY, @Nullable String T_Value) throws Exception {
        switch (type.__getClass__().className) {
            case "int8": {
                return this.ReadInt8(tag);
            }
            case "int16": {
                return this.ReadInt16(tag);
            }
            case "int32": {
                return this.ReadInt32(tag);
            }
            case "int64": {
                return this.ReadInt64(tag);
            }
            case "string": {
                return this.ReadString(tag);
            }
//                case T_Map._t_className:{
//                    return this.ReadMap(tag,T_KEY,T_VALUE);
//                }
        }
        return type;
    }
    public T_Base ReadAny(Integer tag, String type, @Nullable String T_KEY, @Nullable String T_Value) throws Exception {
        switch (type) {
            case "int8": {
                return this.ReadInt8(tag);
            }
            case "int16": {
                return this.ReadInt16(tag);
            }
            case "int32": {
                return this.ReadInt32(tag);
            }
            case "int64": {
                return this.ReadInt64(tag);
            }
            case "string": {
                return this.ReadString(tag);
            }
//                case T_Map._t_className:{
//                    return this.ReadMap(tag,T_KEY,T_VALUE);
//                }
        }
        return null;
    }

    public T_INT8 ReadInt8(Integer tag) {
        this.position += 1;
        byte value = this.originBuf.get(this.position - 1);
        return this.PutTagValToMap(tag, new T_INT8(value));
    }

    public T_INT16 ReadInt16(Integer tag) {
        this.position += 2;
        short value = this.originBuf.getShort(this.position - 2);
        return this.PutTagValToMap(tag, new T_INT16(value));
    }

    public T_INT32 ReadInt32(Integer tag) {
        this.position += 4;
        int value = this.originBuf.getInt(this.position - 4);
        return this.PutTagValToMap(tag, new T_INT32(value));
    }

    public T_INT64 ReadInt64(Integer tag) {
        this.position += 8;
        long value = this.originBuf.getLong(this.position - 2);
        return this.PutTagValToMap(tag, new T_INT64(value));
    }

    public T_Double ReadDouble(Integer tag) {
        this.position += 8;
        long value = this.originBuf.getLong(this.position - 8);
        return this.PutTagValToMap(tag, new T_Double(value));
    }

    public T_String ReadString(Integer tag) {
        this.position += 4;
        int ByteLength = this.originBuf.getInt(this.position - 4);
        ByteBuffer buffer = this.createBuffer(ByteLength);
        byte[] bytes = buffer.array();
        System.arraycopy(this.originBuf.array(), this.position, bytes, 0, ByteLength);
        String s = new String(bytes, StandardCharsets.UTF_8);
        this.position += ByteLength;
        return this.PutTagValToMap(tag, new T_String(s));
    }


    public <R extends T_RStream, V extends T_Base> V ReadStruct(Integer tag, Class<V> target, Class<R> Read) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<R> constructor = Read.getConstructor(ByteBuffer.class);
        this.position += 4;
        int ByteLength = this.originBuf.getInt(this.position - 4);
        ByteBuffer buffer = this.createBuffer(ByteLength);
        byte[] bytes = buffer.array();
        System.arraycopy(this.originBuf.array(), this.position, bytes, 0, ByteLength);
        this.position += ByteLength;
        R rs = constructor.newInstance(ByteBuffer.wrap(bytes));
        Method deserializeMethod = Read.getMethod("DeSerialize");
        T_RStream deserialize = (T_RStream) deserializeMethod.invoke(rs);
        V obj = deserialize.toObj(target);
        return this.PutTagValToMap(tag, obj);
    }

    public <T extends T_Base, R extends T_RStream> T_Vector<T> ReadVector(Integer tag, Class<T> T_TYPE) throws Exception {
//        Class<R> Read = T_Container.getDeclareProtoClass(T_TYPE, "read");
        T_Vector<T> tv = new T_Vector(T_TYPE);
        this.position += 4;
        int ByteLength = this.originBuf.getInt(this.position - 4);
        if (ByteLength == 0) {
            return tv;
        }
        ByteBuffer temp = this.createBuffer(ByteLength);
        byte[] bytes = temp.array();
        System.arraycopy(this.originBuf.array(), this.position, bytes, 0, ByteLength);
        T_Vector<T> ts = T_Vector.streamToObj(ByteBuffer.wrap(bytes), tv, ByteLength);
        return this.PutTagValToMap(tag, ts);
    }
}
