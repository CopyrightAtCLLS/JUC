package synchronizedDemo;

public class SecureStaticSale {
    public static void main(String[] args) {

        new Thread(()->{StaticTicket.sale();},"A").start();
        new Thread(()->{StaticTicket.sale();},"B").start();
        //无论是通过类还是对象调用 静态同步方法，锁的目的都能达到
        StaticTicket staticTicket=new StaticTicket();
        new Thread(()->{staticTicket.sale();},"C").start();
    }
}
class StaticTicket{
    private static int ticket=500;

    public static void sale(){
        while (true) {
            //静态synchronized代码块的锁对象必须是 类.class
            //如果是静态方法，锁对象也是class
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
