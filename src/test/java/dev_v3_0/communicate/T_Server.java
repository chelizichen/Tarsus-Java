package dev_v3_0.communicate;

import dev_v3_0.communicate.handler.T_ClientHandler;
import dev_v3_0.communicate.handler.T_RPC;
import dev_v3_0.communicate.test.SampleImpl;
import dev_v3_0.decorator.Module;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

public class T_Server {
    private void Initialize(Class<?> clazz) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Step 1 先确定abstract类是否有Module注解
        boolean annotationPresent = clazz.getSuperclass().isAnnotationPresent(Module.class);
        if (!annotationPresent) {
            throw new ClassNotFoundException("TargetClass is not a Module");
        }
        // Step 2 创造类实例
        Object instance = clazz.getConstructor().newInstance();
        String moduleName = clazz.getSimpleName();
        System.out.println("Module: " + moduleName + " is Load Success");
        T_RPC.SetModule(moduleName, instance);
    }

    T_Server(Class<?> clazz) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Initialize(clazz);
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

    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println("初始化服务 :" + 24511);
        new T_Server(SampleImpl.class);
    }
}
