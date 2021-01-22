package LockDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SecureTicket {
    public static void main(String[] args) {
        Ticket ticket=new Ticket();
        new Thread(()->{ticket.sale();},"A").start();
        new Thread(()->{ticket.sale();},"B").start();
    }
}
class Ticket{
    private int ticket=100;

    //1、
    Lock lock=new ReentrantLock();

    public void sale(){
        while(true) {
            try {
                //2、
                lock.lock();
                if (ticket > 0) {
                    //同步之后就不要再使用sleep，否则线程只会霸占资源后，进行无意义的睡眠
                    System.out.println(Thread.currentThread().getName() + " 卖掉了 " + ticket + " 号票");
                    ticket--;
                } else break;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //3、
                lock.unlock();
            }
        }
    }

}