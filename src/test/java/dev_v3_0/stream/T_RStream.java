package dev_v3_0.stream;

import dev_v3_0.category.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class T_RStream {
    public ByteBuffer originBuf;
    public Integer position = 0;

    public HashMap<Integer, String> Tag2Field = new HashMap<>();
    public HashMap<String,T_Base> readStreamToObj = new HashMap<String,T_Base>();
    public T_RStream(ByteBuffer originBuf) {
        this.originBuf = originBuf;
    }

    ByteBuffer createBuffer(Integer size) {
        return ByteBuffer.allocate(size);
    }

    <T>T toObj(Class<T> tClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        T_Map<T_Base> tb = new T_Map<>();
        tb.putAll(this.readStreamToObj);
        return tClass.getDeclaredConstructor(T_Map.class).newInstance(tb);
    }

    public <T extends T_Base>T PutTagValToMap(Integer tag,T value){
        String field = this.Tag2Field.get(tag);
        this.readStreamToObj.put(field, value);
        return value;
    }

    public T_INT8 ReadInt8(Integer tag) {
        this.position += 1;
        byte value = this.originBuf.get(this.position - 1);
        return this.PutTagValToMap(tag,new T_INT8(value));
    }

    public T_INT16 ReadInt16(Integer tag) {
        this.position += 2;
        short value = this.originBuf.getShort(this.position - 2);
        return this.PutTagValToMap(tag,new T_INT16(value));
    }

    public T_INT32 ReadInt32(Integer tag) {
        this.position += 4;
        int value = this.originBuf.getInt(this.position - 4);
        return this.PutTagValToMap(tag,new T_INT32(value));
    }

    public T_INT64 ReadInt64(Integer tag) {
        this.position += 8;
        long value = this.originBuf.getLong(this.position - 2);
        return this.PutTagValToMap(tag,new T_INT64(value));
    }

    public T_Double ReadDouble(Integer tag) {
        this.position += 8;
        long value = this.originBuf.getLong(this.position - 8);
        return  this.PutTagValToMap(tag,new T_Double(value));
    }

    public T_String ReadString(Integer tag) {
        this.position += 4;
        int ByteLength = this.originBuf.getInt(this.position - 4);
        ByteBuffer buffer = this.createBuffer(ByteLength);
        byte[] bytes = buffer.array();
        System.arraycopy(this.originBuf.array(), this.position, bytes, 0, ByteLength);
        String s = new String(bytes, StandardCharsets.UTF_8);
        this.position += ByteLength;
        return  this.PutTagValToMap(tag,new T_String(s));
    }


    public <R extends T_RStream, W extends T_WStream, T extends T_JceStruct<R, W>, V> V ReadStruct(Integer tag, T jceStruct,Class<V> vClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<R> read = jceStruct.Read;
        Constructor<R> constructor = read.getConstructor(ByteBuffer.class);
        this.position += 4;
        int ByteLength = this.originBuf.getInt(this.position - 4);
        ByteBuffer buffer = this.createBuffer(ByteLength);
        byte[] bytes = buffer.array();
        System.arraycopy(this.originBuf.array(), this.position, bytes, 0, ByteLength);
        this.position += ByteLength;
        R tRStream = constructor.newInstance(ByteBuffer.wrap(bytes));
        return tRStream.Deserialize().toObj(vClass);
    }

    public <T extends T_Base> T_Vector<T> ReadVector(Integer tag, T T_TYPE) {
        T_Vector<T> tv = new T_Vector<T>(T_TYPE);
        this.position += 4;
        int ByteLength = this.originBuf.getInt(this.position - 4);
        if (ByteLength == 0) {
            return tv;
        }
        ByteBuffer temp = this.createBuffer(ByteLength);
        byte[] bytes = temp.array();
        System.arraycopy(this.originBuf.array(), this.position, bytes, 0, ByteLength);
        T_Vector<T> vector = tv.StreamToObject(ByteBuffer.wrap(bytes), T_TYPE, ByteLength);
        return vector;
    }

    public T_RStream Deserialize() {
        return this;
    }

}
