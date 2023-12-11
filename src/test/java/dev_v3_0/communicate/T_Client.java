package dev_v3_0.communicate;


import java.io.IOException;
import java.net.ServerSocket;

public class T_Client {
    public T_Client() throws IOException {
        ServerSocket serverSocket = new ServerSocket(24511, 20);
    }
}
