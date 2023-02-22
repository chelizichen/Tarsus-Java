package base;

import com.darylteo.rx.promises.java.Promise;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class EventEmitter {

    // 单例
    public static EventEmitter signalEvent = new EventEmitter();

    public static Promise<Object> emit(String eventName,Object args){
        return EventEmitter.signalEvent._emit(eventName,args);
    }


    public ArcThread arcThread = new ArcThread();
    // 事件容器，key为事件名称，value 为事件的监听
    public static Map<String, Function> events = new HashMap<>();

    // 添加监听
    public void on(String eventName, Function event) {
        events.put(eventName,event);
    }

    // 触发事件
    public Promise<Object> _emit(String eventName, Object args) {
        final Function function = events.get(eventName);
        final Promise promise = new Promise<>();
        arcThread.newThread(new Thread(() -> {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                final Object apply = function.apply(args);
                promise.fulfill(apply);
            }
        })).start();
        return promise;
    }
}
