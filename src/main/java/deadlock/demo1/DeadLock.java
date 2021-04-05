package deadlock.demo1;

import java.util.concurrent.TimeUnit;

public class DeadLock {
    public static void main(String[] args) {
        Source source = new Source();
        new Thread(() -> source.write()).start();
        new Thread(() -> source.delete()).start();
    }
}

class Source {
    Object lock1 = new Object();
    Object lock2 = new Object();

    public void write() {
        synchronized (lock1) {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {

            }
        }
    }

    public void delete() {
        synchronized (lock2) {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock1) {

            }
        }
    }
}

