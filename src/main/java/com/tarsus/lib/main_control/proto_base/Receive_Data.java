package com.tarsus.lib.main_control.proto_base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.main_control.load_manager.SingletonRegistry;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.Tarsus;
import com.tarsus.lib.main_control.load_server.impl.TarsusErr;
import com.tarsus.lib.main_control.load_server.impl.TarsusStream;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Receive_Data {
    public static String[] proto = new String[]{"[#1]",
            "[#2]", "[#3]", "[#4]", "[#5]",
            "[#6]", "[#7]", "[#8]", "[#9]",
            "[##]"
    };

    // 拆分 EID 时，需要判断他是否在事件中，这样可以判断他是否是跨服务调用的
    // 当跨服务调用时，会在该系统中生成一个唯一 eid
    // 由于大部分是同步方法，所以当异步方法时，没办法去做类似同步的return ，否则会发生阻塞
    public StringBuffer invoke(StringBuffer stf) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println("recieve-data " + stf);
        final String getId = stf.substring(0, 8);
        if (Tarsus.asyncObserver.has(getId)) {
            String body = stf.substring(8, stf.length() - 8);
            Class<?> ResponseClass = Tarsus.asyncObserver.getResponse(getId);
            Object object = JSONObject.parseObject(body,ResponseClass);
            Tarsus.asyncObserver.emit(getId, (TarsusBodyABS) object);
            return null;
        }

        // 如果含有UID ，则代表为跨服务请求的返回
        // 如果没有，则全局先注册一个
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


        if (!$request.equals(request)) {
            String $err = TarsusErr.Request($request, request);
            stringBuffer.append($err);
            return stringBuffer;
        }

        Class<TarsusBodyABS> request$class = (Class<TarsusBodyABS>) TarsusStream.StreamMap.get(parameterTypes[0].getSimpleName());
        Constructor<?> declaredConstructor = request$class.getConstructor(List.class);

        // 请求Request
        TarsusBodyABS RequestInstance = (TarsusBodyABS) declaredConstructor.newInstance(args);
        RequestInstance.__eid__ = getId;
        $params.add(RequestInstance);
        // 如果注册过了 则代表为跨服务调用的方法，执行callback
        Tarsus.asyncObserver.on(getId, tarsusJsonInf -> {
            try {
                TarsusBodyABS parseToBody = (TarsusBodyABS) tarsusJsonInf;
                String id = getId;
                String body = id + parseToBody.json();
                Tarsus.reset(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        });
        Class<TarsusBodyABS> response$class = (Class<TarsusBodyABS>) TarsusStream.StreamMap.get(parameterTypes[1].getSimpleName());
        Constructor<?> noArgsConst = response$class.getConstructor();
        TarsusBodyABS ResponseInstance = (TarsusBodyABS) noArgsConst.newInstance();
        ResponseInstance.__eid__ = getId;
        $params.add(ResponseInstance);

        Object data = $method.invoke($interface, $params.get(0), $params.get(1));
        if (data == null) {
            return null;
        }
        TarsusBodyABS body = (TarsusBodyABS) data;
        Class<?> return$class = $method.getReturnType();

        if (return$class != response$class) {
            String $err = TarsusErr.Response(return$class.getSimpleName(), response$class.getSimpleName());
            stringBuffer.append($err);
            return stringBuffer;
        }
        stringBuffer.append(body.json());
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


}
