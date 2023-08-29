package com.tarsus.lib.main_control.load_server.impl;

import com.tarsus.lib.lib_decorator.ms.TarsusReflect;
import com.tarsus.lib.main_control.load_server.TarsusBodyABS;
import com.tarsus.lib.main_control.proto_base.Transmit_Data;

import java.util.function.Function;

public class TarsusBaseReflect {
    public String proxy;
    public String interFace;

    public TarsusBaseReflect() {
        TarsusReflect annotation = this.getClass().getAnnotation(TarsusReflect.class);
        this.proxy = annotation.proxy();
        this.interFace = annotation.interFace();

    }

    public <M extends String, T extends TarsusBodyABS, R extends TarsusBodyABS> Transmit_Data ProxySendRequest(M method, T Request, R Response, Function<R, R> callback) {
        return new Transmit_Data<>(this.interFace, this.proxy, method, Request.getClass().getSimpleName(), Request, Response, callback);
    }
}
