package blockingQ;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQdemo {
    public static void main(String[] args) throws InterruptedException {
        test4();
    }
    //add,remove,element方法会抛出异常
    public static void test1(){
        BlockingQueue queue=new ArrayBlockingQueue<>(2);
        System.out.println(queue.add("a"));
        System.out.println(queue.add(2));
//        System.out.println(queue.add("a"));

        System.out.println(queue.remove());
        System.out.println(queue.remove());
//        System.out.println(queue.remove());
    }

    //offer,poll,peek不抛出异常，而是返回false和null
    public static void test2(){
        BlockingQueue queue=new ArrayBlockingQueue<>(2);
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer(2));
        System.out.println(queue.offer("a"));

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

    //put和take会阻塞
    public static void test3() throws InterruptedException {
        BlockingQueue queue=new ArrayBlockingQueue(2);
        queue.put(1);
        queue.put(2);
//        queue.put(3);
        System.out.println(queue.take());
        System.out.println(queue.take());
//        System.out.println(queue.take());
    }

    //offer和poll可以设置超时时间
    public static void test4() throws InterruptedException {
        BlockingQueue queue=new ArrayBlockingQueue<>(2);
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer(2));
        System.out.println(queue.offer("a",2, TimeUnit.SECONDS));

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll(2,TimeUnit.SECONDS));
    }
}
