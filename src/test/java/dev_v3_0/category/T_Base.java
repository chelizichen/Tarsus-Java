package dev_v3_0.category;

import dev_v3_0.stream.T_WStream;

import java.nio.ByteBuffer;

public interface T_Base {
    T_WStream ObjectToStream() throws Exception;
    T_Base StreamToObject(ByteBuffer buf, T_Base T_Value, Integer ByteLength);

    T_Class __getClass__();

    Object GetValue();
}
