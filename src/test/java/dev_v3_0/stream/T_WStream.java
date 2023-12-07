package dev_v3_0.stream;


import dev_v3_0.category.T_Base;
import dev_v3_0.category.T_JceStruct;
import dev_v3_0.category.T_Vector;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class T_WStream {
    public ByteBuffer originBuf;
    public Integer capacity;
    public Integer position;
    public Integer tag = 0;

    public static ByteBuffer createBuffer(Integer size) {
        return ByteBuffer.allocate(size);
    }

    public void allocate(Integer byteLength) {
        if (this.capacity > this.position + byteLength) {
            return;
        }
        this.capacity = Math.max(512, (this.position + byteLength) * 2);
        this.originBuf = T_WStream.createBuffer(this.capacity);
    }

    public void addTag(Integer tag) throws Exception {
        if (!tag.equals(this.tag)) {
            throw new Exception("WriteTagError");
        }
    }

    public void WriteAny(Integer tag, T_Base value) throws Exception {
        switch (value.__getClass__().className) {
            case "int8": {
                this.WriteInt8(tag, (short) value.GetValue());
                break;
            }
            case "int16": {
                this.WriteInt16(tag, (short) value.GetValue());
                break;
            }
            case "int32": {
                this.WriteInt32(tag, (int) value.GetValue());
                break;
            }
            case "int64": {
                this.WriteInt64(tag, (long) value.GetValue());
                break;
            }
            case "string": {
                this.WriteString(tag, (String) value.GetValue());
                break;
            }
        }
    }

    public void WriteInt8(Integer tag, short value) throws Exception {
        this.addTag(tag);
        this.tag++;
        this.position += 1;
        this.allocate(1);
        this.originBuf.put(this.position - 1, (byte) value);
    }

    public void WriteInt16(Integer tag, short value) throws Exception {
        this.addTag(tag);
        this.position += 2;
        this.allocate(2);
        this.originBuf.putShort(this.position - 2, value);
    }

    public void WriteInt32(Integer tag, int value) throws Exception {
        this.addTag(tag);
        this.position += 4;
        this.allocate(4);
        this.originBuf.putInt(this.position - 4, value);
    }

    public void WriteInt64(Integer tag, long value) throws Exception {
        this.addTag(tag);
        this.position += 8;
        this.allocate(8);
        this.originBuf.putLong(this.position - 8, value);
    }

    public void WriteDouble(Integer tag, double value) throws Exception {
        this.addTag(tag);
        this.position += 8;
        this.allocate(8);
        this.originBuf.putDouble(this.position - 8, value);
    }

    public void WriteString(Integer tag, String value) throws Exception {
        this.addTag(tag);
        this.position += 4;
        this.allocate(4);
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        this.originBuf.putInt(this.position - 4, bytes.length);
        Integer i = 0;
        for (byte sByte : bytes) {
            this.originBuf.put(this.position + i, sByte);
            i++;
        }
    }

    public <V extends T_Base, T extends ArrayList<V>> void WriteVector(Integer tag, T ListValue, V T_Value) throws Exception {
        T_Vector<V> value = new T_Vector<>(T_Value);
        boolean isAddAllSuccess = value.addAll(ListValue);
        if (isAddAllSuccess) {
            throw new Exception("add All error");
        }
        T_WStream ws = value.ObjectToStream();
        Integer position = ws.position;
        this.position += 4;
        this.allocate(4);
        this.originBuf.putInt(this.position - 4, position);
        this.allocate(this.position);
    }

    public void WriteBuf(Integer tag, Byte[] bytes) throws Exception {
        if (!tag.equals(-1)) {
            this.addTag(tag);
        }
        this.position += 4;
        this.allocate(4);
        this.originBuf.putInt(this.position - 4, bytes.length);
        this.position += bytes.length;
        this.allocate(bytes.length);

        Integer i = 0;
        for (byte sByte : bytes) {
            this.originBuf.put(this.position + i, sByte);
            i++;
        }
    }

    public void WriteStruct(Integer tag, T_Base value, T_JceStruct TW) throws Exception {
        if (!tag.equals(-1)) {
            this.addTag(tag);
        }
    }

    public static void main(String[] args) {
        // 创建一个容量为10的字节缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(10);
        // 添加数据到缓冲区
        buffer.put((byte) 1);
        buffer.put((byte) 2);
        buffer.put((byte) 3);
        buffer.put((byte) 4);
        buffer.put((byte) 5);

        // 切换缓冲区的模式，从写模式到读模式
        buffer.flip();

        // 从缓冲区读取数据
        while (buffer.hasRemaining()) {
            byte b = buffer.get();
            System.out.println(b);
        }
    }
}
