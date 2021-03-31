package Callable.demo1;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<?> runnableFuture = service.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("This is Runnable, no return value!");
            }
        });
        System.out.println(runnableFuture.get());
        Future<String> callableFuture = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "This is return value of Callalbe!";
            }
        });
        System.out.println(callableFuture.get());
        service.shutdown();
    }
}
