package dev_v3_0.test;

import dev_v3_0.category.*;
import dev_v3_0.stream.T_RStream;
import dev_v3_0.stream.T_WStream;

import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;

public class QueryId implements T_Base {

    public T_INT32 id;
    public BasicInfo basicInfo;
    public static String _t_className = "Struct<QueryId>";

    static {
        T_Container.JCE_STRUCT.put(QueryId._t_className, new T_JceStruct<QueryId.Read, QueryId.Write, QueryId>(QueryId.Read.class, QueryId.Write.class, QueryId.class, QueryId._t_className));
    }

    public <T extends T_Base> QueryId(T_Map<T> readStreamToObj) {
        this.basicInfo = (BasicInfo) readStreamToObj.get("basicInfo");
        this.id = (T_INT32) readStreamToObj.get("id");
    }

    public QueryId() {
        // NoArgsConstructor
    }

    @Override
    public T_Class __getClass__() {
        return null;
    }

    @Override
    public void SetValue(Object value) {

    }

    @Override
    public QueryId GetValue() {
        return this;
    }

    public static class Read extends T_RStream {
        public T_INT32 id;
        public BasicInfo basicInfo;

        public void ScanFields2Tag() {
            this.Tag2Field.put(0, "id");
            this.Tag2Field.put(1, "basicInfo");
        }

        public Read(ByteBuffer originBuf) {
            super(originBuf);
            this.ScanFields2Tag();
        }

        public Read DeSerialize() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
            this.id = this.ReadInt32(0);
            this.basicInfo = this.ReadStruct(1, BasicInfo.class, T_Container.JCE_STRUCT.get(BasicInfo._t_className).Read);
            return this;
        }
    }

    public static class Write extends T_WStream {
        public Write Serialize(QueryId queryId) throws Exception {
            this.WriteInt32(0, queryId.id.GetValue());
            this.WriteStruct(1, queryId.basicInfo, T_Container.JCE_STRUCT.get(BasicInfo._t_className).Write);
            return this;
        }
    }

    public static void main(String[] args) throws Exception {
        QueryId queryId = new QueryId();
        queryId.id = new T_INT32(122933);
        queryId.basicInfo = new BasicInfo();
        queryId.basicInfo.traceId = new T_INT32(1112);
        queryId.basicInfo.token = new T_String("测试token");
        QueryId.Write write = new QueryId.Write();
        QueryId.Write ws = write.Serialize(queryId);
        Read read = new Read(ws.toBuf());
        Read r = read.DeSerialize();
        System.out.println("read  " + r.id.GetValue());
        System.out.println("read  " + r.basicInfo.token.GetValue());
        System.out.println("read  " + r.basicInfo.traceId.GetValue());

    }
}
