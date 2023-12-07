package dev_v3_0.stream;

import java.nio.ByteBuffer;

public class T_RStream {
    public ByteBuffer originBuf;
    public Integer position = 0;

    public T_RStream(ByteBuffer originBuf) {
        this.originBuf = originBuf;
    }

    ByteBuffer createBuffer(Integer size) {
        return ByteBuffer.allocate(size);
    }

    byte ReadInt8(Integer tag) {
        this.position += 1;
        byte value = this.originBuf.get(this.position - 1);
        return value;
    }

    short ReadInt16(Integer tag){
        this.position += 2;
        short value = this.originBuf.getShort(this.position - 2);
        return value;
    }

    int ReadInt32(Integer tag){
        this.position += 4;
        int value = this.originBuf.getInt(this.position - 4);
        return value;
    }

    long ReadInt64(Integer tag){
        this.position += 8;
        long value = this.originBuf.getLong(this.position - 2);
        return value;
    }

    double ReadDouble(Integer tag){
        this.position += 8;
        long value = this.originBuf.getLong(this.position - 8);
        return value;
    }

}
