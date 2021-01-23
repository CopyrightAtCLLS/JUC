package compute;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  synchronized，多个同步函数共享一把锁
 */
public class Compute {
    private static int number = 100;
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<10;i++) {
            int j=i;
            new Thread(() -> {
                System.out.println(j);
            });
        }
        Compute compute=new Compute();
        //add
        new Thread(() -> {
            compute.add();
        }).start();
        new Thread(() -> {
            compute.add();
        }).start();
        //subtract
        new Thread(() -> {
            compute.subtract();
        }).start();
        new Thread(() -> {
            compute.subtract();
        }).start();
        TimeUnit.MILLISECONDS.sleep(200);
        System.out.println(number);
    }

    public synchronized void add() {
        for (int i = 0; i < 500000; i++) {
//            lock.lock();
            number++;
//            lock.unlock();
        }
    }

    public synchronized  void subtract() {
        for (int i = 0; i < 500000; i++) {
//            lock.lock();
            number--;
//            lock.unlock();
        }
    }
}
