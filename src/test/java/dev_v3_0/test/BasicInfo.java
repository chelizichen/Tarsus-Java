package dev_v3_0.test;

import dev_v3_0.category.T_Container;
import dev_v3_0.category.T_INT32;
import dev_v3_0.category.T_JceStruct;
import dev_v3_0.category.T_String;
import dev_v3_0.stream.T_RStream;
import dev_v3_0.stream.T_WStream;

import java.nio.ByteBuffer;

public class BasicInfo {
    public T_String token;
    public T_INT32 traceId;
    public static String _t_className = "Struct<BasicInfo>";

    static {
        T_Container.Set(BasicInfo._t_className, new T_JceStruct<BasicInfo.Read, BasicInfo.Write>(BasicInfo.Read.class, BasicInfo.Write.class, BasicInfo._t_className));
    }

    public static class Read extends T_RStream {
        public T_String token;
        public T_INT32 traceId;

        public Read(ByteBuffer originBuf) {
            super(originBuf);
        }

        public Read DeSerialize() {
            this.token = this.ReadString(0);
            this.traceId = this.ReadInt32(1);
            return this;
        }
    }

    public static class Write extends T_WStream {
        public Write Serialize(BasicInfo queryId) throws Exception {
            this.WriteString(0, queryId.token.GetValue());
            this.WriteInt32(1, queryId.traceId.GetValue());
            return this;
        }
    }

    public static void main(String[] args) throws Exception {
        BasicInfo basicInfo = new BasicInfo();
        basicInfo.token = new T_String("hello world");
        basicInfo.traceId = new T_INT32(122933);
        Write write = new Write();
        Write serialize = write.Serialize(basicInfo);
        ByteBuffer buf = serialize.toBuf();
        Read read = new Read(buf);
        Read read1 = read.DeSerialize();
        System.out.println("read1.token  " + read1.token.GetValue());
        System.out.println("read1.traceId  " + read1.traceId.GetValue());
    }
}
