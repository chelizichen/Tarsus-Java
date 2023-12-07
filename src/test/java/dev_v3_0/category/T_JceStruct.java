package dev_v3_0.category;

import dev_v3_0.stream.T_RStream;
import dev_v3_0.stream.T_WStream;

public class T_JceStruct<R extends T_RStream,W extends T_WStream> {
    public Class<R> Read;
    public Class<W> Write;
    public String _t_className;

    public T_JceStruct(Class<R> read, Class<W> write, String _t_className) {
        Read = read;
        Write = write;
        this._t_className = _t_className;
    }
}
