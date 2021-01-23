package syncDetails;

import java.util.concurrent.TimeUnit;

/**
 * 即使使用同一个对象调用静态同步方法和普通同步方法
 * 他们使用的锁对象依然不同
 * 静态同步方法默认使用class,而普通同步方法使用调用者作为锁对象
 *
 * 普通同步代码块如果使用class作为锁对象，则和静态同步方法使用了同一把锁
 */
public class Printer {
    public static void main(String[] args) throws InterruptedException {
        Data data=new Data();
        new Thread(()->{data.printStatic();}).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{data.printNormal();}).start();
        new Thread(()->{data.print_1();}).start();
        new Thread(()->{data.print_2();}).start();
    }
}
class Data{
    public static synchronized void printStatic(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("静态同步方法");
    }

    public synchronized void printNormal(){
        System.out.println("普通同步方法");
    }
    public void print_1(){
        synchronized (this){
            System.out.println("使用this作为锁的普通同步代码块");
        }
    }
    public void print_2(){
        synchronized (Data.class){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("使用Data.class作为锁的普通同步代码块");
        }
    }
}
