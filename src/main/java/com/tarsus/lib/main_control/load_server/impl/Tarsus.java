package com.tarsus.lib.main_control.load_server.impl;

import com.tarsus.lib.main_control.load_config.LoadConfig;
import com.tarsus.lib.main_control.load_manager.SingletonRegistry;
import com.tarsus.lib.main_control.load_server.TarsusInf;
import com.tarsus.lib.main_control.proto_base.AsyncObserver;
import com.tarsus.lib.main_control.proto_base.Receive_Data;
import com.tarsus.lib.main_control.proto_base.SyncObserver;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Tarsus implements TarsusInf {

    public static LoadConfig config = null;
    public static Receive_Data receive$data = new Receive_Data();
    public static AsyncObserver asyncObserver = new AsyncObserver();
    public static SyncObserver syncObserver = new SyncObserver();

    public static BufferedWriter bw = null;
    public static StringBuffer stf = null;
    public static String str = "";
    public Tarsus(Class<?> target$class) {
        LoadConfig config = new LoadConfig();
        Tarsus.config = config;
        LoadConfig.packageName = target$class.getPackage().getName();
    }

    @Override
    public void LoadInterFace(Class<?>[] interfaces) {
        // 创建实例
        SingletonRegistry.setMethods(interfaces);
    }

    @Override
    public void LoadStruct() {
        Tarsus.config.LoadStruct();
    }

    @Override
    public void LoadServer() throws InvocationTargetException, IllegalAccessException {
        try {
            ServerSocket serverSocket = new ServerSocket(Tarsus.config.port, 20);
            System.out.println("Tarsus Server started at localhost:" + Tarsus.config.port);
            InputStreamReader inSR = null;
            OutputStreamWriter outSW = null;

            while (true) {
                // 使用ServerSocket对象中的方法accept，获取到请求的客户端对象Socket。
                Socket socket = serverSocket.accept();

                inSR = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(inSR);
                outSW = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);

                Tarsus.bw = new BufferedWriter(outSW);
                Tarsus.stf = new StringBuffer();
                Tarsus.str = "";
                while ((str = br.readLine()) != null) {
                    stf.append(str);
                    // 执行 结束 语句 并且 拆分相关字节流
                    if (str.endsWith("[#ENDL#]")) {
                        final StringBuffer data = receive$data.invoke(Tarsus.stf);
                        if(data != null){
                            System.out.println("recieve - data " + data.toString());
                            Tarsus.reset(data.toString());
                        }
                    }
                }
            }
        } catch (IOException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void reset(String data) throws IOException {
        Tarsus.bw.write(data);
        Tarsus.bw.flush();
        Tarsus.stf.delete(0, Tarsus.stf.length());
    }

}
