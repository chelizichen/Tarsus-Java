package dev_v3_0.test;

import dev_v3_0.category.T_Container;
import dev_v3_0.category.T_INT32;
import dev_v3_0.category.T_INT8;
import dev_v3_0.category.T_JceStruct;
import dev_v3_0.stream.T_RStream;
import dev_v3_0.stream.T_WStream;

import java.nio.ByteBuffer;

public class QueryId {
    public T_INT32 id;
    public static String  _t_className = "Struct<QueryId>";

    static {
        T_Container.Set(QueryId._t_className,new T_JceStruct<QueryId.Read,QueryId.Write>(QueryId.Read.class, QueryId.Write.class,QueryId._t_className));
    }

    public static class Read extends T_RStream {
        public T_INT32 id;

        public Read(ByteBuffer originBuf) {
            super(originBuf);
        }
        public Read DeSerialize(){
            this.id = this.ReadInt32(0);
            return this;
        }
    }

    public static class Write extends T_WStream {
        public Write Serialize(QueryId queryId) throws Exception {
            this.WriteInt32(0,queryId.id.GetValue());
            return this;
        }
    }

    public static void main(String[] args) throws Exception {
        QueryId queryId = new QueryId();
        queryId.id = new T_INT32(122933);
        QueryId.Write write = new QueryId.Write();
        QueryId.Write ws = write.Serialize(queryId);
        Read read = new Read(ws.toBuf());
        Read r = read.DeSerialize();
        System.out.printf("read  "+ r.id.GetValue());
    }
}
