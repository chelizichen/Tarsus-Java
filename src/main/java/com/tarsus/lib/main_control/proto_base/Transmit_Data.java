package com.tarsus.lib.main_control.proto_base;

import com.alibaba.fastjson2.annotation.JSONField;
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
    @JSONField(serialize = false)
    public T Response;
    @JSONField(serialize = false)
    public Function callBack;

    public Transmit_Data(String proxy, String interFace, String method, String request, T data, T Response, Function callBack) {
        this.proxy = proxy;
        this.interFace = interFace;
        this.method = method;
        this.timeout = timeout;
        this.request = request;
        this.data = data;
        this.Response = Response;
        this.callBack = callBack;
        try {
            String PadProxy = padRight(proxy, 16);
            CrossRequest(PadProxy, callBack);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // 由网关自行判断是Java还是NodeJS ，NodeJS处已经经过 FAST-JSON序列化加速了，
    // Java这边暂时没办法去处理，所以先直接先打成JSON，data 再给NodeJS那里去处理
    public void CrossRequest(String proxy, Function<T, T> callBack) throws IOException {
        String eid = UUID.randomUUID().toString().substring(0, 8);
        System.out.println("Generated eid: " + eid);
        String json = this.json();

        StringBuffer reset$data = new StringBuffer();
        reset$data.append(eid, 0, 8);
        System.out.println(proxy.length());
        reset$data.append(proxy);
        reset$data.append(json);
        // Java 向网关写入数据
        System.out.println("reset-data  " + reset$data);
        Tarsus.reset(reset$data.toString());
        // 并且生成一个异步CallBack回调
        Tarsus.asyncObserver.on(eid, data ->
                callBack.apply((T) data)
        );
        Tarsus.asyncObserver.onResponse(eid, Response.getClass());
        // 这里注册一个 eid 的事件，同步等待回调？或者再优化
    }

    public static String padRight(String originalString, int length) {
        return String.format("%-" + length + "s", originalString);
    }
}
