package com.tarsus.lib.main_control.load_server.impl;

import com.alibaba.fastjson.JSON;
import com.tarsus.lib.__test__.src.struct.Basic;
import com.tarsus.lib.main_control.load_config.LoadConfig;
import com.tarsus.lib.main_control.load_manager.SingletonRegistry;
import com.tarsus.lib.main_control.load_server.TarsusInf;
import com.tarsus.lib.main_control.load_server.TarsusJsonInf;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Tarsus implements TarsusInf {

    protected static LoadConfig config = null;

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
//        String name = Basic.class.getName();
//        System.out.println("name - "+name);
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

                BufferedWriter bw = new BufferedWriter(outSW);
                StringBuffer stf = new StringBuffer();
                String str = "";
                while ((str = br.readLine()) != null) {
                    stf.append(str);
                    // 执行 结束 语句 并且 拆分相关字节流
                    if (str.endsWith("[#ENDL#]")) {
                        System.out.println("recieve data - "+str);
                        final StringBuffer data = this.invoke(stf);
                        System.out.println("结束的代码" + data);
                        bw.write(data.toString());
                        bw.flush();
                        stf.delete(0, stf.length());
                    }
                }
            }
        } catch (IOException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public StringBuffer invoke(StringBuffer stf) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        final String getId = stf.substring(0, 4);

        String interFace = this.unpkgHead(0, stf);
        String method = this.unpkgHead(1, stf);
        String timeout = this.unpkgHead(2, stf);
//        Integer body_len = Integer.valueOf(this.unpkgHead(3, stf));
        String request = this.unpkgHead(4, stf, true);

        Object $interface = SingletonRegistry.Interface$Maps.get(interFace);
        Method $method = SingletonRegistry.Methods$Maps.get(interFace + method);
        int body_index = stf.indexOf("[##]") + 4;
        String buf = stf.substring(body_index, stf.length() - 8);
        List args = this.unPackageBody(buf);

        Class<?>[] parameterTypes = $method.getParameterTypes();
        ArrayList<Object> $params = new ArrayList<>(); // 实参
        String $request = parameterTypes[0].getSimpleName();

        if(!$request.equals(request)){
            String $err = TarsusErr.Request($request, request);
            StringBuffer stringBuffer = new StringBuffer();

            stringBuffer.append(getId);
            stringBuffer.append( $err);
            return stringBuffer;
        }

        Class<?> request$class = TarsusStream.StreamMap.get(parameterTypes[0].getSimpleName());
        Constructor<?> declaredConstructor = request$class.getConstructor(List.class);

        // 请求Request
        Object RequestInstance = declaredConstructor.newInstance(args);
        $params.add(RequestInstance);

        Class<?> response$class = TarsusStream.StreamMap.get(parameterTypes[1].getSimpleName());
        Constructor<?> noArgsConst = response$class.getConstructor();
        Object ResponseInstance = noArgsConst.newInstance();
        $params.add(ResponseInstance);

        TarsusJsonInf data = (TarsusJsonInf) $method.invoke($interface, $params.get(0), $params.get(1));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getId);
        stringBuffer.append(data.json());
        return stringBuffer;
    }

    private String unpkgHead(int start, StringBuffer data) {
        int start_index = data.indexOf(LoadConfig.proto[start]);
        int start_next = data.indexOf(LoadConfig.proto[start + 1]);
        String head = data.substring(start_index + LoadConfig.proto[start].length(), start_next);
        return head;
    }

    private List<?> unPackageBody(String stf) {
        System.out.println(stf);
        return JSON.parseArray(stf);
    }


    private String unpkgHead(int start, StringBuffer data, boolean isEnd) {
        int start_index = data.indexOf(LoadConfig.proto[start]);
        int start_next;
        if (isEnd) {
            start_next = data.indexOf(LoadConfig.proto[LoadConfig.proto.length - 1]);
        } else {
            start_next = data.indexOf(LoadConfig.proto[start + 1]);
        }
        String head = data.substring(start_index + LoadConfig.proto[start].length(), start_next);
        return head;
    }
}
