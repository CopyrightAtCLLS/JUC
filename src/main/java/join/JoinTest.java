package join;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

/**
 * 如果打开子线程的同步代码块，Main线程就会等待子线程释放锁后，才能获取到锁对象
 * 可见，join()需要获取监听对象的锁对象
 */
public class JoinTest implements Runnable {
    public static void main(String[] sure) throws InterruptedException {
        Thread t = new Thread(new JoinTest());
        long start = System.currentTimeMillis();
        t.start();
        t.join(1000);//等待线程t 1000毫秒
        System.out.println(System.currentTimeMillis() - start);//打印出时间间隔
        System.out.println("Main finished");//打印主线程结束
    }

    @Override
    public void run() {
        synchronized (currentThread()) {
            for (int i = 1; i <= 5; i++) {
                try {
                    sleep(1000);//睡眠5秒，循环是为了方便输出信息
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("睡眠" + i);
            }
            System.out.println("TestJoin finished");//t线程结束
        }
    }
}