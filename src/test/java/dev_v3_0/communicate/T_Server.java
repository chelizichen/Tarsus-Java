package dev_v3_0.communicate;

import dev_v3_0.communicate.handler.T_ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class T_Server {
    T_Server() throws IOException {
        int port = 12345; // 监听的端口号
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            // 等待客户端连接请求
            Socket clientSocket = serverSocket.accept();
            // 创建线程处理客户端请求
            new Thread(new T_ClientHandler(clientSocket)).start();
        }
    }
}
