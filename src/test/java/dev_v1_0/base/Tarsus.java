package dev_v1_0.base;

import com.alibaba.fastjson.JSON;
import dev_v1_0.decorator.TarsusMsApplication;
import dev_v1_0.decorator.async.Async;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 启动类的父类
 */
public  class Tarsus {

    public TarsusYaml tarsusYaml = new TarsusYaml();

    public TarsusCache tarsusCache = new TarsusCache();

    public Tarsus() {
        // 再设置IocMap
        TarsusBaseInterFace.IocMap.put(tarsusYaml.getClass().getSimpleName(),tarsusYaml);
        TarsusBaseInterFace.IocMap.put(tarsusCache.getClass().getSimpleName(),tarsusCache);
    }

    /**
     * 协议头  { interFace Method timeout bodyLen }
     */
    static String[] proto = new String[]{"[#1]",
            "[#2]", "[#3]", "[#4]", "[#5]",
            "[#6]", "[#7]", "[#8]", "[#9]",
            "[##]"
    };



    public static void run(Class<?> clazz,String[] args){

        Tarsus tarsus = new Tarsus();

        // *************** 获得包名 ******************
        Package aPackage = clazz.getPackage();
        String pkg_name = aPackage.getName();
        TarsusYaml.pkg_name = pkg_name;
        // *************** 获得包名 ******************


        // 读取配置文件
        tarsus.tarsusYaml.read_config();

        boolean hasAnnotation = clazz.isAnnotationPresent(TarsusMsApplication.class);
        if (hasAnnotation) {
            TarsusMsApplication testAnnotation = clazz.getAnnotation(TarsusMsApplication.class);
            Integer PORT = 0;
            if(tarsus.tarsusYaml.port >= 0){
                PORT = tarsus.tarsusYaml.port;
            }else {
                PORT = testAnnotation.port();
            }
            try {
                tarsus.createServer(PORT);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param SonClass 子类
     * 创建 Arc 微服务
     */
    public <T extends Tarsus> void boost(Class<T> SonClass) {

        boolean hasAnnotation = SonClass.isAnnotationPresent(TarsusMsApplication.class);
        System.out.println("111"+tarsusYaml.port);
        if (hasAnnotation) {
            TarsusMsApplication testAnnotation =  SonClass.getAnnotation(TarsusMsApplication.class);
            // 拿到 Port
            Integer PORT = tarsusYaml.port | testAnnotation.port();
            try {
                this.createServer(PORT);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    protected void loadInterFace(Class<TarsusBaseInterFace>[] classes){
        System.out.println(classes.length);
        for (Class<TarsusBaseInterFace> aClass : classes) {
            try {
                aClass.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        final int size = TarsusBaseInterFace.ClazzMap.size();
        System.out.println("总共有"+size+"个代理类");
    }

    public void loadEvents(Class<?>[] classes){
        // 加载异步任务
        for (Class<?> aClass : classes) {
            try {
                // 加载无参构造
                final Object instance = aClass.getConstructor().newInstance();
                final Method[] declaredMethods = aClass.getDeclaredMethods();
                for (Method declaredMethod : declaredMethods) {
                    final boolean annotationPresent = declaredMethod.isAnnotationPresent(Async.class);
                    if(annotationPresent){
                        final String value = declaredMethod.getAnnotation(Async.class).value();
                        TarsusEvents.signalEvent.on(value, o -> {
                            Object data = null;
                            try {
                                data =  declaredMethod.invoke(instance,o);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                            return data;
                        });
                    }
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }

        }
    }

    private void createServer(Integer port) throws IllegalAccessException, InvocationTargetException {
        try {
            ServerSocket serverSocket = new ServerSocket(port, 20);
            System.out.println("Tarsus Server started at localhost:"+port);
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
                        final StringBuffer data = this.beforeInvoke(stf);
                        System.out.println("结束的代码"+data);
                        bw.write(data.toString());
                        bw.flush();
                        stf.delete(0,stf.length());
                    }
                }
            }
        } catch (IOException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public StringBuffer beforeInvoke(StringBuffer stf) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        final String getId = stf.substring(0, 4);

        String interFace = this.unpkgHead(0, stf);
        String method = this.unpkgHead(1, stf);
        String timeout = this.unpkgHead(2, stf);
//        Integer body_len = Integer.valueOf(this.unpkgHead(3, stf));
        String _request = this.unpkgHead(4, stf, true);

        TarsusBaseInterFace TarsusInstance = TarsusBaseInterFace.ClazzMap.get(interFace);
        int body_index = stf.indexOf("[##]")+4;
        String buf = stf.substring(body_index, stf.length() - 8);
        List list = this.unPackageBody(buf);

        final String data = TarsusInstance.invokeMethod(interFace, method, list,_request);
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(getId);
        stringBuffer.append(data);
        return stringBuffer;
    }

    private String unpkgHead(int start, StringBuffer data) {
        int start_index = data.indexOf(Tarsus.proto[start]);
        int start_next = data.indexOf(Tarsus.proto[start + 1]);
        String head = data.substring(start_index + proto[start].length(), start_next);
        return head;
    }

    private List<?> unPackageBody(String stf){
        System.out.println(stf);
        return JSON.parseArray(stf);
    }


    private String unpkgHead(int start, StringBuffer data, boolean isEnd) {
        int start_index = data.indexOf(Tarsus.proto[start]);
        int start_next;
        if (isEnd) {
            start_next = data.indexOf(Tarsus.proto[proto.length - 1]);
        } else {
            start_next = data.indexOf(Tarsus.proto[start + 1]);
        }
        String head = data.substring(start_index + proto[start].length(), start_next);
        return head;
    }

}
