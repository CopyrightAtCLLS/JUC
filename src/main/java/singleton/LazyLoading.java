package singleton;

import sun.misc.Unsafe;

public class LazyLoading {
    private LazyLoading() {
    }

    ;

    /**
     * volatile是为了防止对象初始化时出现指令重排
     * 对象初始化过程：1、分配内存空间 2、执行构造方法 3、对象指向该空间
     * 如果出现指令重排，可能按照132的顺序执行
     * 假如执行到3时，另一个线程执行了if(instance==null)，则会直接return instance;
     * 然而此时对象并没有创建完成
     */
    private volatile static LazyLoading instance;

    private static LazyLoading getInstance() {
        if (instance == null) {
            synchronized (LazyLoading.class) {
                if (instance == null)
                    instance = new LazyLoading();
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        LazyLoading lazyLoading=LazyLoading.getInstance();
    }
}
