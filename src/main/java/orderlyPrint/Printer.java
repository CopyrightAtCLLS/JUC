package orderlyPrint;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Printer {
    public static void main(String[] args) {
        Data data=new Data();
        new Thread(()->{for(int i=0;i<10;i++) data.printA();},"Printer 1 =>").start();
        new Thread(()->{for(int i=0;i<10;i++) data.printB();},"Printer 2 =>").start();
        new Thread(()->{for(int i=0;i<10;i++) data.printC();},"Printer 3 =>").start();
    }
}

class Data{
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    int flag=1;

    public void printA(){
        lock.lock();
        try {
            while(flag!=1){
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+" A");
            flag=2;
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try {
            while(flag!=2){
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+" B");
            flag=3;
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try {
            while(flag!=3){
                condition.await();
            }
            System.out.println(Thread.currentThread().getName()+" C");
            flag=1;
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
