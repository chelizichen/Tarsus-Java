package TEST;


import com.darylteo.rx.promises.java.Promise;
import com.darylteo.rx.promises.java.functions.PromiseAction;

public class RxTest {

    public static void main(String[] args) {
        makePromise("123").then(new PromiseAction<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    public static Promise<String> makePromise(String value){
        Promise<String> objectPromise = new Promise<String>();
        objectPromise.fulfill("1111");
        return objectPromise;
    }

}

