package dev_v1_0.TEST;

import dev_v1_0.base.TarsusEvents;
import com.darylteo.rx.promises.java.Promise;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class EventEmitterTest {
    public static void main(String[] args) {


    }
    public static void test_emit(){
        final TarsusEvents eventEmitter = new TarsusEvents();

        eventEmitter.on("Thread1", new Function() {
            @Override
            public Object apply(Object o) {
                return o;
            }
        });

        eventEmitter.on("Thread2", o -> o);

        final Map o1 = new HashMap<>();
        o1.put("异步线程 ->>>>","111");
        final Map o2 = new HashMap<>();
        o2.put("异步线程 ->>>>","222");


        final Promise emit = eventEmitter.emit("Thread1",o1);
        final Promise emit1 = eventEmitter.emit("Thread2",o2);

        emit.then(o -> {
            System.out.println(o);
        });

        emit1.then(o -> {
            System.out.println(o);
        });

        System.out.println("普通线程 ->>> 1111");
        System.out.println("普通线程 ->>> 2222");
    }
}
