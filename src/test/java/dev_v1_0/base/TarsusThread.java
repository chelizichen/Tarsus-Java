package dev_v1_0.base;

import dev_v1_0.decorator.ioc.Collect;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Collect
public class TarsusThread implements ThreadFactory {
    private final int MAX_THREAD;

    public TarsusThread(){
        MAX_THREAD = 100;
    }

    private final AtomicInteger count = new AtomicInteger(0);

    public TarsusThread(int maxThread) {
        MAX_THREAD = maxThread;
    }

    @Override
    public Thread newThread(Runnable r) {
        int incrementAndGet = count.incrementAndGet();
        if(incrementAndGet > MAX_THREAD)
        {
            count.decrementAndGet();
            return null;
        }

        return new Thread(r);
    }
}
