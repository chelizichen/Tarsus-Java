package dev_v3_0.category;

import dev_v3_0.stream.T_WStream;
import io.reactivex.rxjava3.annotations.Nullable;

import java.nio.ByteBuffer;

public interface T_Base {

    T_Class __getClass__();

    <T>T GetValue();

    void SetValue(Object value);
}
