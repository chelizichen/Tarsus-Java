package dev_v3_0.test;

import dev_v3_0.category.*;
import dev_v3_0.stream.T_RStream;
import dev_v3_0.stream.T_WStream;

import java.nio.ByteBuffer;

public class QueryIds {
    public T_Vector<T_INT32> ids;
    public static String _t_className = "Sample.Struct<QueryIds>";

    static {
        T_Container.JCE_STRUCT.put(QueryIds._t_className, new T_JceStruct(QueryIds.Read.class, QueryIds.Write.class, QueryIds.class, QueryId._t_className));
    }

    public <T extends T_Base> QueryIds(T_Map<T> readStreamToObj) {
        this.ids = (T_Vector<T_INT32>) readStreamToObj.get("ids");
    }

    public QueryIds() {
        // NoArgsConstructor
    }


    public static class Read extends T_RStream {
        public T_Vector<T_INT32> ids;

        public void ScanFields2Tag() {
            this.Tag2Field.put(0, "ids");
        }

        public Read(ByteBuffer originBuf) {
            super(originBuf);
            this.ScanFields2Tag();
        }

        public Read DeSerialize() throws Exception {
            this.ids = this.ReadVector(0, T_INT32.class);
            System.out.println("this.ids" + this.ids);
            return this;
        }
    }

    public static class Write extends T_WStream {
        public Write Serialize(QueryIds queryIds) throws Exception {
            this.WriteVector(0, queryIds.ids);
            return this;
        }
    }

    public static void main(String[] args) throws Exception {
        QueryIds queryIds = new QueryIds();
        queryIds.ids = new T_Vector<>(T_INT32.class);
        queryIds.ids.push(11);
        queryIds.ids.push(22);
        queryIds.ids.push(33);

        Write write = new Write();
        Write serialize = write.Serialize(queryIds);
        ByteBuffer buf = serialize.toBuf();
        System.out.println("serialize.position : " + serialize.position);
        Read read = new Read(buf).DeSerialize();
        QueryIds obj = read.toObj(QueryIds.class);
        System.out.println(obj);
        System.out.println(obj.ids);
        for (T_INT32 id : obj.ids) {
            System.out.println(id.GetValue());
        }
    }
}
