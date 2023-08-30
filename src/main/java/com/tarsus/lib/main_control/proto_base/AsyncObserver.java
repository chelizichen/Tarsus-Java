package com.tarsus.lib.main_control.proto_base;

import com.tarsus.lib.main_control.load_server.TarsusBodyABS;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import java.util.function.Function;

public class AsyncObserver<T extends TarsusBodyABS>{
    private final Map<String, Function<T, CompletableFuture<T>>> listenersMap = new HashMap<>();

    // 注册一个监听器
    public void on(String uid, Function<T, CompletableFuture<T>> listener) {
        listenersMap.put(uid,listener);
    }

    // 异步地发出一个事件，并返回每个监听器的返回值的CompletableFuture列表
    public CompletableFuture<T> emit(String eid, T data) {
        Function<T, CompletableFuture<T>> listeners = listenersMap.get(eid);
        this.listenersMap.remove(eid);
        if (listeners != null) {
            return listeners.apply(data);
        }
        return null;
    }

    public void delete(String uid){
        listenersMap.remove(uid);
    }

    public boolean has(String uid){
        return listenersMap.containsKey(uid);
    }

//    public static void main(String[] args) {
//        AsyncObserver<String, Integer> observer = new AsyncObserver<>();
//
//        // 注册监听器
//        observer.on("1", data -> CompletableFuture.supplyAsync(() -> {
//            // 模拟网络请求
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            return data.length();
//        }));
//
//        // 异步发出事件并接收返回值
//        CompletableFuture<Integer> futures = observer.emit("1", "Hello, World!");
//
//        futures.thenAccept(result -> {
//                System.out.println("Received result: " + result);
//            });
//    }


}
