package com.tarsus.example.TEST;


import com.darylteo.rx.promises.java.Promise;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.disposables.Disposable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class RxTest {

    public static void main(String[] args) {
        TestFlower();
//        TestObserver();
//        makePromise("111").then(new PromiseAction<String>() {
//            @Override
//            public void call(String s) {
//
//                System.out.println(s);
//            }
//        });
//        System.out.println(222);
    }

    public static Promise<String> makePromise(String value){
        Promise<String> objectPromise = new Promise<String>();
        objectPromise.fulfill(value);
        return objectPromise;
    }

    public static void TestObserver(){
        Observable mObservable= Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onComplete();
            }
        });

        Observer mObserver=new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("事件被监听");
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("next--"+value);
            }

            @Override
            public void onError(Throwable e) {}
            @Override
            public void onComplete() {
                System.out.println("完成任务");
            }

        };


        mObservable.subscribe(mObserver);

    }

    public static void TestFlower(){
        Flowable.range(0,4)
                .subscribe(new Subscriber<Integer>() {
                    Subscription s1;
                    @Override
                    public void onSubscribe(Subscription s) {
                        System.out.println("调用订阅方法");
                        s1 = s;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("调用NEXT方法");
                        System.out.println("integer"+integer);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("调用完成方法");
                    }
                });
    }
}

