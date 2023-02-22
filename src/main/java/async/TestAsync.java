package async;

import decorator.async.Async;

public class TestAsync {

    @Async("hello")
    public Object data(Object args){
        return args;
    }
}
