package com.tarsus.lib.main_control.proto_base;

import com.alibaba.fastjson.JSON;
import com.tarsus.dev_v1_0.base.inf.TarsusJson;
import com.tarsus.lib.main_control.load_manager.SingletonRegistry;
import com.tarsus.lib.main_control.load_server.TarsusJsonInf;
import com.tarsus.lib.main_control.load_server.impl.Tarsus;
import com.tarsus.lib.main_control.load_server.impl.TarsusErr;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Receive_Data {
    public static String[] proto = new String[]{"[#1]",
            "[#2]", "[#3]", "[#4]", "[#5]",
            "[#6]", "[#7]", "[#8]", "[#9]",
            "[##]"
    };

    public StringBuffer invoke(StringBuffer stf) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        final String getId = stf.substring(0, 8);
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

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getId);


        if(!$request.equals(request)){
            String $err = TarsusErr.Request($request, request);
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
        Class<?> return$class = $method.getReturnType();

        if(return$class != response$class){
            String $err = TarsusErr.Response(return$class.getSimpleName(), response$class.getSimpleName());
            stringBuffer.append( $err);
            return stringBuffer;
        }
        stringBuffer.append(data.json());
        System.out.println("return - data " + stringBuffer.toString());
        return stringBuffer;
    }

    private String unpkgHead(int start, StringBuffer data) {
        int start_index = data.indexOf(Receive_Data.proto[start]);
        int start_next = data.indexOf(Receive_Data.proto[start + 1]);
        String head = data.substring(start_index + Receive_Data.proto[start].length(), start_next);
        return head;
    }

    private List<?> unPackageBody(String stf) {
        return JSON.parseArray(stf);
    }


    private String unpkgHead(int start, StringBuffer data, boolean isEnd) {
        int start_index = data.indexOf(Receive_Data.proto[start]);
        int start_next;
        if (isEnd) {
            start_next = data.indexOf(Receive_Data.proto[Receive_Data.proto.length - 1]);
        } else {
            start_next = data.indexOf(Receive_Data.proto[start + 1]);
        }
        String head = data.substring(start_index + Receive_Data.proto[start].length(), start_next);
        return head;
    }

    // 由网关自行判断是Java还是NodeJS ，NodeJS处已经经过 FAST-JSON序列化加速了，
    // Java这边暂时没办法去处理，所以先直接先打成JSON，data 再给NodeJS那里去处理
    public  static<Req extends TarsusJson,Res> Res CrossRequest(Object curr,Req Request, Res Response) throws IOException {
        String eid = UUID.randomUUID().toString().substring(0,8);
        String json = Request.json();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(eid);
        stringBuffer.append(curr);
        stringBuffer.append(json);
        Tarsus.bw.write(stringBuffer.toString());
        // 这里注册一个 eid 的事件，同步等待回调？或者再优化
        return Response;
    }
}
