package blockingQ;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQdemo {
    public static void main(String[] args) {
        test2();
    }
    //add和remove方法会抛出异常
    public static void test1(){
        BlockingQueue queue=new ArrayBlockingQueue<>(2);
        System.out.println(queue.add("a"));
        System.out.println(queue.add(2));
//        System.out.println(queue.add("a"));

        System.out.println(queue.remove());
        System.out.println(queue.remove());
//        System.out.println(queue.remove());
    }

    //offer和poll不抛出异常，而是返回false和null
    public static void test2(){
        BlockingQueue queue=new ArrayBlockingQueue<>(2);
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer(2));
        System.out.println(queue.offer("a"));

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

    }
}
