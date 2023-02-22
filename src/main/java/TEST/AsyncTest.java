package TEST;


public class AsyncTest {
    public static void main(String[] args) {
        System.out.println(1);
        new Test().start();
        new Test1().start();
        System.out.println(2);
    }
}



class Test extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("aaaa"+i);
        }
    }
}

class Test1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("bbb"+i);
        }
    }
}

