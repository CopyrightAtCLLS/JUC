package rwLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWrite {
    public static void main(String[] args) {
        Cache cache=new Cache();
        for(int i=0;i<6;i++) {
            int temp=i;
            new Thread(() -> {
                cache.write(temp+"", temp+"");
            }, String.valueOf(i + 1)).start();
        }

        for(int i=0;i<6;i++) {
            int temp=i;
            new Thread(() -> {
                cache.read(temp+"");
            }, String.valueOf(i + 1)).start();
        }
    }
}

class Cache {
    private volatile Map<String,Object> cache=new HashMap<>();
    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    public void write(String key,Object value) {
        readWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+" 正在写入");
        cache.put(key,value);
        System.out.println(Thread.currentThread().getName()+" 写入完成");
        readWriteLock.writeLock().unlock();
    }

    public void read(String key) {
        readWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName()+" 正在读取");
        cache.get(key);
        System.out.println(Thread.currentThread().getName()+" 读取完成");
        readWriteLock.readLock().unlock();
    }
}
