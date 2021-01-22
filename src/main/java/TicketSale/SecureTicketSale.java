package TicketSale;

public class SecureTicketSale {
    public static void main(String[] args) {
        SecureTicket secureTicket =new SecureTicket();
        new Thread(()->{
            secureTicket.sale();},"A").start();
        new Thread(()->{
            secureTicket.sale();},"B").start();
        new Thread(()->{
            secureTicket.sale();},"C").start();
    }
}
class SecureTicket {
    private int ticket=50;

    //创建一个锁对象,一定要放在run方法(使用的方法)外部
    Object obj=new Object();

    public void sale(){
        while(true) {
            //同步代码块必须放在while内部，否则成为了其余线程不可能参与抢票
            synchronized (obj) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 卖掉了 " + ticket + " 号票");
                    ticket--;
                } else break;
            }
        }
    }
}

