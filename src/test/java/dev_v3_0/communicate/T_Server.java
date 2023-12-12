package dev_v3_0.communicate;

import dev_v3_0.category.T_Container;
import dev_v3_0.category.T_JceStruct;
import dev_v3_0.communicate.handler.T_ClientHandler;
import dev_v3_0.communicate.handler.T_RPC;
import dev_v3_0.communicate.test.Sample;
import dev_v3_0.test.BasicInfo;
import dev_v3_0.test.QueryId;
import dev_v3_0.test.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class T_Server {
    static {
        T_Container.JCE_STRUCT.put(QueryId._t_className, new T_JceStruct(QueryId.Read.class, QueryId.Write.class, QueryId.class, QueryId._t_className));
        T_Container.JCE_STRUCT.put(User._t_className, new T_JceStruct(User.Read.class, User.Write.class, User.class, User._t_className));
        T_Container.JCE_STRUCT.put(BasicInfo._t_className, new T_JceStruct(BasicInfo.Read.class, BasicInfo.Write.class, BasicInfo.class, BasicInfo._t_className));
        T_RPC.SetModule("Sample", new Sample());
        T_RPC.SetMethod("getUserById", T_Container.JCE_STRUCT.get(QueryId._t_className), T_Container.JCE_STRUCT.get(User._t_className));
        System.out.println("初始化服务 :" + 24511);
    }

    T_Server() throws IOException {
        int port = 24511; // 监听的端口号
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            // 等待客户端连接请求
            Socket clientSocket = serverSocket.accept();
            // 创建线程处理客户端请求
            new Thread(
                    new T_ClientHandler(clientSocket)
            ).start();
        }
    }

    public static void main(String[] args) throws IOException {
        new T_Server();
    }
}
