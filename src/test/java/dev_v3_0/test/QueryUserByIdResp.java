package dev_v3_0.test;

import dev_v3_0.category.*;
import dev_v3_0.stream.T_RStream;
import dev_v3_0.stream.T_WStream;

import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;

public class QueryUserByIdResp implements T_Base {

    public T_INT16 code;
    public User user;
    public T_String message;

    public static String _t_className = "Struct<User>";

    static {
        T_Container.JCE_STRUCT.put(QueryUserByIdResp._t_className, new T_JceStruct<QueryUserByIdResp.Read, QueryUserByIdResp.Write,QueryUserByIdResp>(QueryUserByIdResp.Read.class, QueryUserByIdResp.Write.class, QueryUserByIdResp.class,QueryUserByIdResp._t_className));
    }

    public <T extends T_Base> QueryUserByIdResp(T_Map<T> readStreamToObj) {
        this.code = (T_INT16) readStreamToObj.get("code");
        this.user = (User) readStreamToObj.get("user");
        this.message = (T_String) readStreamToObj.get("message");
    }

    public QueryUserByIdResp() {
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
    public QueryUserByIdResp GetValue() {
        return this;
    }

    public static class Read extends T_RStream {
        public T_INT16 code;
        public User user;
        public T_String message;

        public void ScanFields2Tag() {
            this.Tag2Field.put(0, "code");
            this.Tag2Field.put(1, "user");
            this.Tag2Field.put(2, "message");
        }

        public Read(ByteBuffer originBuf) {
            super(originBuf);
            this.ScanFields2Tag();
        }

        public Read DeSerialize() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
            this.code = this.ReadInt16(0);
            this.user = this.ReadStruct(1, User.class, T_Container.JCE_STRUCT.get(User._t_className).Read);
            this.message = this.ReadString(2);
            return this;
        }
    }

    public static class Write extends T_WStream {
        public Write Serialize(QueryUserByIdResp queryUserByIdResp) throws Exception {
            this.WriteInt16(0, queryUserByIdResp.code.GetValue());
            this.WriteStruct(1, queryUserByIdResp.user.GetValue(), T_Container.JCE_STRUCT.get(User._t_className).Write);
            this.WriteString(2, queryUserByIdResp.message.GetValue());
            return this;
        }
    }

    public static void main(String[] args) throws Exception {
        QueryUserByIdResp queryUserByIdResp = new QueryUserByIdResp();
        queryUserByIdResp.code = new T_INT16((short) 0);
        queryUserByIdResp.message = new T_String("asd");
        queryUserByIdResp.user = new User();
        queryUserByIdResp.user.userId = new T_INT32(1);
        queryUserByIdResp.user.userAddress = new T_String("wuhan");
        queryUserByIdResp.user.userName = new T_String("cheng");
        queryUserByIdResp.user.phoneNumber = new T_String("13478812281");
        queryUserByIdResp.user.createTime = new T_String("2023-12-08");
        queryUserByIdResp.user.status = new T_INT8(1);

        Write write = new Write();
        Write serialize = write.Serialize(queryUserByIdResp);

        Read read = new Read(serialize.toBuf()).DeSerialize();
        System.out.println(read.code.GetValue());
        System.out.println(read.user.userId.GetValue());
        System.out.println(read.user.createTime.GetValue());
        System.out.println(read.user.userAddress.GetValue());
        System.out.println(read.user.userName.GetValue());
        System.out.println(read.user.status.GetValue());
        System.out.println(read.user.phoneNumber.GetValue());
        System.out.println(read.message.GetValue());
    }
}
