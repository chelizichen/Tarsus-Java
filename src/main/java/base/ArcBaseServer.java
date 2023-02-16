package base;


import config.ret;
import decorator.ArcServerApplication;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ArcBaseServer {

    static String[] proto = new String[]{"[#1]",
            "[#2]", "[#3]", "[#4]", "[#5]",
            "[#6]", "[#7]", "[#8]", "[#9]",
            "[##]"
    };

    static String[] size = new String[]{
            "#a#",
            "#b#", "#c#", "#d#",
            "#e#", "#f#", "#g#", "#h#", "#i#",
            "#j#", "#k#", "#l#", "#m#",
            "#n#", "#o#", "#p#", "#q#", "#r#", "#s#",
            "#t#", "#u#", "#v#", "#w#", "#x#", "#y#",
            "#z#",
    };


    public <T extends ArcBaseServer> void boost(Class<T> SonClass) {
        boolean hasAnnotation = SonClass.isAnnotationPresent(ArcServerApplication.class);
        if (hasAnnotation) {
            ArcServerApplication testAnnotation =  SonClass.getAnnotation(ArcServerApplication.class);
            // 拿到 Port
            Integer PORT = testAnnotation.port();
            this.setContainers();
            try {
                this.createServer(PORT);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private void setContainers(){
        final int size = ArcBaseClass.ClazzMap.size();
        System.out.println("总共有"+size+"个代理类");
    }

    private void createServer(Integer port) throws IllegalAccessException, InvocationTargetException {
        try {
            ServerSocket serverSocket = new ServerSocket(port, 20);

            InputStreamReader inSR = null;
            OutputStreamWriter outSW = null;

            while (true) {
                // 使用ServerSocket对象中的方法accept，获取到请求的客户端对象Socket。
                Socket socket = serverSocket.accept();

                inSR = new InputStreamReader(socket.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(inSR);
                outSW = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
                BufferedWriter bw = new BufferedWriter(outSW);
                StringBuffer stf = new StringBuffer();

                String str = "";
                while ((str = br.readLine()) != null) {
                    str = str.trim();
                    stf.append(str);

                    // 执行 结束 语句 并且 拆分相关字节流
                    if (str.endsWith("[#ENDL#]")) {
                        final ret data = this.beforeInvoke(stf);
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

    public ret beforeInvoke(StringBuffer stf){
        String interFace = this.unpkgHead(0, stf);
        String method = this.unpkgHead(1, stf);
        String timeout = this.unpkgHead(2, stf);

        ArcBaseClass ArcInstance = ArcBaseClass.ClazzMap.get(interFace);
        int index = stf.indexOf("[##]");
        String buf = stf.substring(index + 4, stf.length() - 8);
        List list = this.unpkgBody(buf);
        final ret data = ArcInstance.invokeMethod(interFace, method, list);

        return data;
    }

    private String unpkgHead(int start, StringBuffer data) {
        int start_index = data.indexOf(ArcBaseServer.proto[start]);
        int start_next = data.indexOf(ArcBaseServer.proto[start + 1]);
        String head = data.substring(start_index + proto[start].length(), start_next);
        return head;
    }


    private String unpkgHead(int start, StringBuffer data, boolean isEnd) {
        int start_index = data.indexOf(ArcBaseServer.proto[start]);
        int start_next;
        if (isEnd) {
            start_next = data.indexOf(ArcBaseServer.proto[proto.length - 1]);
        } else {
            start_next = data.indexOf(ArcBaseServer.proto[start + 1]);
        }
        String head = data.substring(start_index + proto[start].length(), start_next);
        return head;
    }

    protected List unpkgBody(String buf) {
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

    private void __read_rpc__() {

    }


}

class Testclass{
    public static void main(String[] args) {

        String a = "#a#1#b##a#tom#b#jump#c#12#d#[1,2,3,4,5]#z##c#2#z#";
        String b = "#a##a#tom#b#jump#c#12#d#[1,2,3,4,5]#z##b#End#z#";
        String c = "#a##a#tom#b#jump#c#12#d#[1,2,3,4,5]#z#" +
                "#b#tom#c#jump#d##a#tom#b#jump#c#12#d#[1,2,3," +
                "4,5]#z##e##a#tom#b#jump#c#12#d#[1,2,3,4,5]#e##a#t" +
                "om#b#jump#c#12#d#[1,2,3,4,5]#z##z##f#12#g#[1,2,3,4," +
                "5]#h##a#tom#b#jump#c#12#d#[1,2,3,4,5]#e##a#tom#b#jump" +
                "#c#12#d#[1,2,3,4,5]#e##a#tom#b#jump#c#12#d#[1,2,3,4,5]#e##a" +
                "#tom#b#jump#c#12#d#[1,2,3,4,5]#z##z##z##z##z#";

        final ArcBaseServer arcBaseServer = new ArcBaseServer();
//        final List list = arcBaseServer.unpkgBody(c);
//        final List list1 = arcBaseServer.unpkgBody(a);
        final List list2 = arcBaseServer.unpkgBody(b);
//        System.out.println(list);
//        System.out.println(list1);
        System.out.println(list2);

    }
}
