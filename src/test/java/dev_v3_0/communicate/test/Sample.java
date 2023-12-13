package dev_v3_0.communicate.test;

import dev_v3_0.category.T_Container;
import dev_v3_0.category.T_JceStruct;
import dev_v3_0.communicate.handler.T_RPC;
import dev_v3_0.decorator.Module;
import dev_v3_0.test.BasicInfo;
import dev_v3_0.test.QueryId;
import dev_v3_0.test.User;

@Module
public abstract class Sample {
    static {
        T_Container.JCE_STRUCT.put(QueryId._t_className, new T_JceStruct(QueryId.Read.class, QueryId.Write.class, QueryId.class, QueryId._t_className));
        T_Container.JCE_STRUCT.put(User._t_className, new T_JceStruct(User.Read.class, User.Write.class, User.class, User._t_className));
        T_Container.JCE_STRUCT.put(BasicInfo._t_className, new T_JceStruct(BasicInfo.Read.class, BasicInfo.Write.class, BasicInfo.class, BasicInfo._t_className));
        T_RPC.SetMethod("getUserById", T_Container.JCE_STRUCT.get(QueryId._t_className), T_Container.JCE_STRUCT.get(User._t_className));
    }

    public abstract User getUserById(T_RPC.T_Context ctx, QueryId req);
}
