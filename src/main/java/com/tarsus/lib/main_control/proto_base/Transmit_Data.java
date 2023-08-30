package com.tarsus.lib.main_control.proto_base;

import com.alibaba.fastjson.JSONObject;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.load_server.impl.Tarsus;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.UUID;
import java.util.function.Function;

@NoArgsConstructor
public class Transmit_Data<T extends TarsusBodyABS> extends TarsusBodyABS {
    public String proxy;
    public String interFace;
    public String method;
    public String timeout = "60000";
    public T data;
    public String request;
    public T Response;
    public Function callBack;

    public Transmit_Data(String proxy, String interFace, String method, String request, T data, T Response, Function callBack) {
        Transmit_Data<T> transmit = new Transmit_Data<T>();
        transmit.proxy = proxy;
        transmit.interFace = interFace;
        transmit.method = method;
        transmit.timeout = timeout;
        transmit.request = request;
        transmit.data = data;
        transmit.Response = Response;
        transmit.callBack = callBack;
        try {
            CrossRequest(interFace, transmit, callBack);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String json() {
        Object o = JSONObject.toJSON(this);
        return o.toString();
    }

    // 由网关自行判断是Java还是NodeJS ，NodeJS处已经经过 FAST-JSON序列化加速了，
    // Java这边暂时没办法去处理，所以先直接先打成JSON，data 再给NodeJS那里去处理
    public void CrossRequest(String interFace, Transmit_Data Request, Function<T,T> callBack) throws IOException {
        String eid = UUID.randomUUID().toString().substring(0, 8);
        String json = Request.json();
        String stringBuffer = eid +
                interFace +
                json;
        // Java 向网关写入数据
        Tarsus.reset(stringBuffer);
        // 并且生成一个异步CallBack回调
        Tarsus.asyncObserver.on(eid, data ->
            callBack.apply((T) data)
        );
        // 这里注册一个 eid 的事件，同步等待回调？或者再优化
    }
}
