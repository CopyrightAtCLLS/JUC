package callable.demo2;

import java.util.concurrent.*;

public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        //Callable
        FutureTask futureTask = new FutureTask(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.MILLISECONDS.sleep(1000);
                return "The return value of Callable";
            }
        });
        service.submit(futureTask);
        System.out.println(futureTask.get(1200, TimeUnit.MILLISECONDS) + "\n");

        final String tmp = "returnValue";
        futureTask = new FutureTask(() -> {
            System.out.println("Runnable Thread...");
        }, tmp);
        service.submit(futureTask);
        System.out.println(futureTask.get());

        service.shutdown();
    }
}
