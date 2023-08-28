package com.tarsus.lib.main_control.proto_base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class SyncObserver<T, R> {
    private final Map<String, Function<T, R>> listenersMap = new HashMap<>();

    // 注册一个监听器
    public void on(String uid, Function<T, R> listener) {
        listenersMap.put(uid,listener);
    }

    // 发出一个事件，并返回每个监听器的返回值列表
    public R emit(String uid, T data) {
        Function<T, R> listeners = listenersMap.get(uid);
        if (listeners != null) {
            R apply = listeners.apply(data);
            return apply;
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
//        SyncObserver<String, Integer> observer = new SyncObserver<>();
//
//        // 注册监听器
//        observer.on("1", data -> data.length());
//        observer.on("1", data -> data.hashCode());
//        observer.on("2", data -> data.indexOf(" "));
//
//        // 发出事件并接收返回值
//        List<Integer> results1 = observer.emit("1", "Hello, World!");
//        System.out.println(results1); // [13, some hash code]
//
//        List<Integer> results2 = observer.emit("2", "Java Programming");
//        System.out.println(results2); // [4]
//    }
}
