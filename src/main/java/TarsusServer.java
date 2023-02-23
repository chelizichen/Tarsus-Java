import async.TestAsync;
import base.TarsusBaseServer;
import decorator.TarsusServerApplication;
import register.Hello;
import register.Test;


@TarsusServerApplication(port = 9811)
public class TarsusServer extends TarsusBaseServer {
    public static void main(String[] args) {
        TarsusServer c = new TarsusServer();
        c.loadInterFace(new Class[]{Hello.class, Test.class});
        c.loadEvents(new Class[]{TestAsync.class});
        c.boost(TarsusServer.class);
    }
}
