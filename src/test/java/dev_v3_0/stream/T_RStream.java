package dev_v3_0.stream;

import dev_v3_0.category.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class T_RStream {
    public ByteBuffer originBuf;
    public Integer position = 0;

    public HashMap<String, T_Base> readStreamToObj = new HashMap<>();

    HashMap<String, T_Base> toObj() {
        return this.readStreamToObj;
    }

    public T_RStream(ByteBuffer originBuf) {
        this.originBuf = originBuf;
    }

    ByteBuffer createBuffer(Integer size) {
        return ByteBuffer.allocate(size);
    }

    public byte ReadInt8(Integer tag) {
        this.position += 1;
        byte value = this.originBuf.get(this.position - 1);
        this.readStreamToObj.put(tag.toString(), new T_Byte(value));
        return value;
    }

    public short ReadInt16(Integer tag) {
        this.position += 2;
        short value = this.originBuf.getShort(this.position - 2);
        this.readStreamToObj.put(tag.toString(), new T_INT16(value));
        return value;
    }

    public T_INT32 ReadInt32(Integer tag) {
        this.position += 4;
        int value = this.originBuf.getInt(this.position - 4);
        T_INT32 tVal = new T_INT32(value);
        this.readStreamToObj.put(tag.toString(), tVal);
        return tVal;
    }

    public long ReadInt64(Integer tag) {
        this.position += 8;
        long value = this.originBuf.getLong(this.position - 2);
        this.readStreamToObj.put(tag.toString(), new T_INT64(value));
        return value;
    }

    public double ReadDouble(Integer tag) {
        this.position += 8;
        long value = this.originBuf.getLong(this.position - 8);
        this.readStreamToObj.put(tag.toString(), new T_Double(value));
        return value;
    }

    public T_String ReadString(Integer tag) {
        this.position += 4;
        int ByteLength = this.originBuf.getInt(this.position - 4);
        ByteBuffer buffer = this.createBuffer(ByteLength);
        byte[] bytes = buffer.array();
        System.arraycopy(this.originBuf.array(), this.position, bytes, 0, ByteLength);
        String s = new String(bytes, StandardCharsets.UTF_8);
        T_String value = new T_String(s);
        this.position += ByteLength;
        this.readStreamToObj.put(tag.toString(), value);
        return value;
    }


    public HashMap<String, T_Base> ReadStruct(Integer tag, T_JceStruct jceStruct) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<T_RStream> read = jceStruct.Read;
        Constructor<T_RStream> constructor = read.getConstructor(ByteBuffer.class);
        this.position += 4;
        int ByteLength = this.originBuf.getInt(this.position - 4);
        ByteBuffer buffer = this.createBuffer(ByteLength);
        byte[] bytes = buffer.array();
        System.arraycopy(this.originBuf.array(), this.position, bytes, 0, ByteLength);
        this.position += ByteLength;
        T_RStream tRStream = constructor.newInstance(ByteBuffer.wrap(bytes));
        this.readStreamToObj.put(tag.toString(), (T_Base) tRStream.Deserialize().toObj());
        return tRStream.Deserialize().toObj();
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
