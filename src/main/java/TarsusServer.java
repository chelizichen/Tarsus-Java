import base.Tarsus;
import decorator.TarsusMsApplication;


@TarsusMsApplication
public class TarsusServer{
    public static void main(String[] args) {
        Tarsus.run(TarsusServer.class,args);
    }
}
