package threadPool;

import java.util.concurrent.*;

public class Pool {
    public static void main(String[] args) {
//        ExecutorService threadPool= Executors.newSingleThreadExecutor();//单线程线程池
//        ExecutorService threadPool= Executors.newFixedThreadPool(3);  //固定大小线程池
//        ExecutorService threadPool= Executors.newCachedThreadPool();  //可变大小线程池

        ExecutorService threadPool = new ThreadPoolExecutor(
                1,
                3,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
//                  ThreadPoolExecutor.CallerRunsPolicy()
//                  ThreadPoolExecutor.DiscardPolicy()
//                  ThreadPoolExecutor.DiscardOldestPolicy()

        try {
            //i的合法上界=阻塞队列大小+最大线程池大小
            for (int i = 0; i < 6; i++) {
                    threadPool.execute(() -> {System.out.println(Thread.currentThread().getName() + " 正在运行");});
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
