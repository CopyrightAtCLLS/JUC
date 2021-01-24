package semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Parking {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore=new Semaphore(3);
        for(int i=0;i<6;i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("第" + Thread.currentThread().getName() + "辆车抢到车位");
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("第" + Thread.currentThread().getName() + "辆车离开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i + 1)).start();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
