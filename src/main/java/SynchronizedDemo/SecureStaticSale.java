package SynchronizedDemo;

public class SecureStaticSale {
    public static void main(String[] args) {
        new Thread(()->{StaticTicket.sale();},"A").start();
        new Thread(()->{StaticTicket.sale();},"B").start();
    }
}
class StaticTicket{
    private static int ticket=10;

    public static void sale(){
        while (true) {
            synchronized (StaticTicket.class) {
                //同步代码块必须放在while内部，否则成为了其余线程不可能参与抢票
                if (ticket > 0) {
                    //同步之后就不要再使用sleep，否则线程只会霸占资源后，进行无意义的睡眠
                    System.out.println(Thread.currentThread().getName() + " 卖掉了 " + ticket + " 号票");
                    ticket--;
                } else break;
            }
        }
    }
}
