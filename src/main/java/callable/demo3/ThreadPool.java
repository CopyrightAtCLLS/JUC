package callable.demo3;

import java.util.concurrent.*;

/**
 * 1、execute()线程中如果抛出异常，会直接在控制台打印
 * <p>
 * 2、submit()线程如果出现异常不会在控制台打印，且不会阻塞父线程执行
 * 但是如果调用future.get()，则会阻塞父线程，并获取到异常信息
 */
public class ThreadPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<?> future = service.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("This is submit(), and an exception is going to be thrown...");
                int i = 1 / 0;
            }
        });
//        System.out.println(future.get()); //调用此方法会阻塞main线程
        System.out.println("-------------------------------------------------------------------");

        service.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("This is execute(), and an exception is going to be thrown...");
                int i = 1 / 0;
            }
        });
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("\nFinished");
        service.shutdown();
    }
}
