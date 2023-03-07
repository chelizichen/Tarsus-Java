package com.tarsus.example.base;

import com.tarsus.example.config.ret;
import com.tarsus.example.decorator.TarsusMsApplication;
import com.tarsus.example.decorator.async.Async;
import com.tarsus.example.decorator.ioc.Inject;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

    /**
     * 数据体
     */
    static String[] size = new String[]{
            "#a#",
            "#b#", "#c#", "#d#",
            "#e#", "#f#", "#g#", "#h#", "#i#",
            "#j#", "#k#", "#l#", "#m#",
            "#n#", "#o#", "#p#", "#q#", "#r#", "#s#",
            "#t#", "#u#", "#v#", "#w#", "#x#", "#y#",
            "#z#",
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
                        bw.write(data.toString());
                        bw.flush();
                        stf.delete(0,stf.length());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public StringBuffer beforeInvoke(StringBuffer stf){

        final String getId = stf.substring(0, 4);
        System.out.println("getId"+getId);
        String interFace = this.unpkgHead(0, stf);
        String method = this.unpkgHead(1, stf);
        String timeout = this.unpkgHead(2, stf);

        TarsusBaseInterFace ArcInstance = TarsusBaseInterFace.ClazzMap.get(interFace);
        int index = stf.indexOf("[##]");
        String buf = stf.substring(index + 4, stf.length() - 8);
        List list = this.unpkgBody(buf);
        final ret data =(ret) ArcInstance.invokeMethod(interFace, method, list);
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(getId);
        stringBuffer.append(data.toString());
        return stringBuffer;
    }

    private String unpkgHead(int start, StringBuffer data) {
        int start_index = data.indexOf(Tarsus.proto[start]);
        int start_next = data.indexOf(Tarsus.proto[start + 1]);
        String head = data.substring(start_index + proto[start].length(), start_next);
        return head;
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

    public List unpkgBody(String buf) {
        List args = new ArrayList();
        int init = 0;
        int start = buf.indexOf(size[init]);
        while (true) {
            String end_str = buf.substring(start, start + 3);
            boolean isEnd = end_str.equals(size[size.length - 1]);
            if (isEnd) {
                break;
            }
            int next = buf.indexOf(size[init + 1], start);

            if (next == -1) {
                if (start + size[init].length() == buf.length()) {
                    break;
                }
                String sub_pkg = buf.substring(start, start + 6);
                boolean is_un_pkg = sub_pkg.equals(size[init] + size[0]);
                // 判断是否为未分割的参数
                if (is_un_pkg) {
                    String un_pkg = buf.substring(start + 3, buf.length() - 3);
                    List list = this.unpkgBody(un_pkg);
                    args.add(init, list);
                } else {
                    String substring = buf.substring(start + 3, buf.length() - 3);
                    args.add(init, substring);
                }
                break;
            } else {
                boolean isObject = buf.substring(start, start + 6).equals(size[init] + size[0]);
                if (isObject) {

                    final String currEndStr = size[size.length - 1] + size[init + 1];
                    final String breakEndStr = size[size.length - 1] + size[size.length - 1];
                    int end = buf.indexOf(currEndStr);


                    String un_pkg = buf.substring(start + 3, end + 3);
                    List getargs = this.unpkgBody(un_pkg);
                    args.add(init, getargs);
                    start = end + 3;
                } else {
                    String getargs = buf.substring(start + 3, next);
                    args.add(init, getargs);
                    start = next;
                }
            }
            init++;
        }
        return args;


    }
}
