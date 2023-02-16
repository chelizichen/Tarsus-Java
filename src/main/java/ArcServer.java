import base.ArcBaseServer;
import config.ret;
import decorator.ArcServerApplication;
import register.Hello;
import register.Test;

@ArcServerApplication(port = 9811)
public class ArcServer extends ArcBaseServer {
    public static void main(String[] args) {
        ArcServer c = new ArcServer();
        final Hello hello = new Hello();
        final Test test = new Test();
        c.boost(ArcServer.class);

    }
}
