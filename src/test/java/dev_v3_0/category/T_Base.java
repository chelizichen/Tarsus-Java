package dev_v3_0.category;

import dev_v3_0.stream.T_WStream;

public interface T_Base {
    T_WStream ObjectToStream() throws Exception;
    <K>K StreamToObject();

    T_Class __getClass__();

    Object GetValue();
}
