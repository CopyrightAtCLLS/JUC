import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("JUC");

        new Thread(()->{
            System.out.println("A");
        }).start();
        new Thread(()->{
            System.out.println("B");
        }).start();
        new Thread(()->{
            System.out.println("C");
        }).start();
    }
}
