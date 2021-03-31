package callable.demo1;

import java.util.concurrent.*;

public class Intro {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        //Runnable
        Future<?> runnableFuture = service.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("This is Runnable, no return value!");
            }
        });
        System.out.println(runnableFuture.get()+"\n");

        //Callable
        Future<String> callableFuture = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.MILLISECONDS.sleep(1000);
                return "This is return value of Callalbe!";
            }
        });
        //get是阻塞方法
        System.out.println(callableFuture.get(1200, TimeUnit.MILLISECONDS));


        System.out.println("Finished!");
        service.shutdown();
    }
}
