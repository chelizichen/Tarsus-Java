package dev_v3_0.test;

import dev_v3_0.category.*;
import dev_v3_0.stream.T_RStream;
import dev_v3_0.stream.T_WStream;

import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;

public class User implements T_Base {

    public T_INT32 userId;
    public T_String userName;
    public T_String phoneNumber;
    public T_String userAddress;
    public T_String createTime;
    public T_INT8 status;
    public static String _t_className = "Sample.Struct<User>";

    static {
        T_Container.JCE_STRUCT.put(User._t_className, new T_JceStruct<User.Read, User.Write, User>(User.Read.class, User.Write.class, User.class, User._t_className));
    }

    public <T extends T_Base> User(T_Map<T> readStreamToObj) {
        this.userId = (T_INT32) readStreamToObj.get("userId");
        this.userName = (T_String) readStreamToObj.get("userName");
        this.phoneNumber = (T_String) readStreamToObj.get("phoneNumber");
        this.userAddress = (T_String) readStreamToObj.get("userAddress");
        this.createTime = (T_String) readStreamToObj.get("createTime");
        this.status = (T_INT8) readStreamToObj.get("status");
    }

    public User() {
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
    public User GetValue() {
        return this;
    }

    public static class Read extends T_RStream {
        public T_INT32 userId;
        public T_String userName;
        public T_String phoneNumber;
        public T_String userAddress;
        public T_String createTime;
        public T_INT8 status;

        public void ScanFields2Tag() {
            this.Tag2Field.put(0, "userId");
            this.Tag2Field.put(1, "userName");
            this.Tag2Field.put(2, "phoneNumber");
            this.Tag2Field.put(3, "userAddress");
            this.Tag2Field.put(4, "createTime");
            this.Tag2Field.put(5, "status");
        }

        public Read(ByteBuffer originBuf) {
            super(originBuf);
            this.ScanFields2Tag();
        }

        public Read DeSerialize() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
            this.userId = this.ReadInt32(0);
            this.userName = this.ReadString(1);
            this.phoneNumber = this.ReadString(2);
            this.userAddress = this.ReadString(3);
            this.createTime = this.ReadString(4);
            this.status = this.ReadInt8(5);
            return this;
        }
    }

    public static class Write extends T_WStream {
        public Write Serialize(User user) throws Exception {
            this.WriteInt32(0, user.userId.GetValue());
            this.WriteString(1, user.userName.GetValue());
            this.WriteString(2, user.phoneNumber.GetValue());
            this.WriteString(3, user.userAddress.GetValue());
            this.WriteString(4, user.createTime.GetValue());
            this.WriteInt8(5, user.status.GetValue());
            return this;
        }
    }

    public static void main(String[] args) throws Exception {

    }
}
