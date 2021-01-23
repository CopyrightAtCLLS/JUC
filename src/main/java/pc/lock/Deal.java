package pc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SuppressWarnings("all")
public class Deal {
    public static void main(String[] args) {
        Data data = new Data();

        //Producers
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                    data.produce();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                    data.produce();
            }
        }, "B").start();

        //Consumers
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                    data.consume();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                    data.consume();
            }
        }, "B").start();
    }
}
@SuppressWarnings("all")
class Data {
    private int number=0;

    Lock lock=new ReentrantLock();
    Condition condition=lock.newCondition();

    public void produce()  {
        try {
            lock.lock();
            //await方法一定要放在while中,防止虚假唤醒
            while(number!=0){
                condition.await();
            }
            number++;
            System.out.println("Producer "+Thread.currentThread().getName()+" => "+number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consume()  {
        try {
            lock.lock();
            while(number==0){
                condition.await();
            }
            number--;
            System.out.println("Consumer "+Thread.currentThread().getName()+" => "+number);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}