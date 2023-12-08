package dev_v3_0.test;

import dev_v3_0.category.*;
import dev_v3_0.decorator.DefineField;
import dev_v3_0.stream.T_RStream;
import dev_v3_0.stream.T_WStream;

import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.HashMap;

public class QueryId implements T_Base {

    public T_INT32 id;
    public BasicInfo basicInfo;
    public static String  _t_className = "Struct<QueryId>";

    static {
        T_Container.Set(QueryId._t_className,new T_JceStruct<QueryId.Read,QueryId.Write>(QueryId.Read.class, QueryId.Write.class,QueryId._t_className));
    }

    public<T extends T_Base> QueryId(T_Map<T> readStreamToObj){
        this.basicInfo =  readStreamToObj.get("basicInfo").GetValue();
        this.id = readStreamToObj.get("id").GetValue();
    }

    public QueryId(){
        // NoArgsConstructor
    }

    @Override
    public T_WStream ObjectToStream() throws Exception {
        return null;
    }

    @Override
    public T_Base StreamToObject(ByteBuffer buf, T_Base T_Value, Integer ByteLength) {
        return null;
    }

    @Override
    public T_Class __getClass__() {
        return null;
    }

    @Override
    public QueryId GetValue() {
        return null;
    }

    public static class Read extends T_RStream {
        public T_INT32 id;
        public BasicInfo basicInfo;
        public void ScanFields2Tag(){
            this.Tag2Field.put(0,"id");
            this.Tag2Field.put(1,"basicInfo");
        }

        public Read(ByteBuffer originBuf) {
            super(originBuf);
            this.ScanFields2Tag();
        }
        public Read DeSerialize() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
            this.id = this.ReadInt32(0);
            T_JceStruct<BasicInfo.Read,BasicInfo.Write> jceStruct = T_Container.JCE_STRUCT.get(BasicInfo._t_className);
            this.basicInfo = this.ReadStruct(1,jceStruct,BasicInfo.class);
            return this;
        }
    }

    public static class Write extends T_WStream {
        public Write Serialize(QueryId queryId) throws Exception {
            this.WriteInt32(0,queryId.id.GetValue());
            this.WriteStruct(1,queryId.basicInfo.GetValue(),T_Container.JCE_STRUCT.get(BasicInfo._t_className));
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
