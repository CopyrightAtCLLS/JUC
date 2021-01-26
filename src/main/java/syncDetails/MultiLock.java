package syncDetails;

import java.util.concurrent.TimeUnit;

/**
 * 分别执行两个自增方法，结果都是30000。
 * 但是同时执行它们的话，结果往往<30000*2
 * 根据测试可以得出，加锁只是针对某一同步方法（或者是同步代码块）的具体执行，而非代码块中指定的内存（也就是变量num）
 */
public class MultiLock {

    private static int num=0;
    static class Data{
        public synchronized static void staticAdd(){
            for(int i=0;i<10000;i++)
            num++;
        }
        public synchronized void add(){
            for(int i=0;i<10000;i++)
            num++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Data data=new Data();
        for(int i=0;i<3;i++){
            new Thread(()->{data.staticAdd();}).start();
            new Thread(()->{data.add();}).start();
        }
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println(num);
    }
}
