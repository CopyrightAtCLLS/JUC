package synchronizedDemo;

import static java.lang.System.exit;

@SuppressWarnings("all")
public class SecureTicketSale {
    public static void main(String[] args) {
        SecureTicket secureTicket = new SecureTicket();
        System.out.println(secureTicket);
        //Sale()
        new Thread(() -> { secureTicket.sale(); }, "A").start();
        new Thread(() -> { secureTicket.sale(); }, "B").start();
        new Thread(() -> { secureTicket.sale(); }, "C").start();

        //Sale_1()
//        new Thread(() -> { while(true) secureTicket.sale_1(); }, "A").start();
//        new Thread(() -> { while(true) secureTicket.sale_1(); }, "B").start();
//        new Thread(() -> { while(true) secureTicket.sale_1(); }, "C").start();


    }
}

class SecureTicket {
    private int ticket = 50;

    //创建一个锁对象,一定要放在run方法(使用的方法)外部
    Object obj=new Object();

    public void sale() {
        System.out.println(this);
        while (true) {
            //同步代码块必须放在while内部，否则成为了其余线程不可能参与抢票
            synchronized (this) {
                if (ticket > 0) {
                    //同步之后就不要再使用sleep，否则线程只会霸占资源后，进行无意义的睡眠
                    System.out.println(Thread.currentThread().getName() + " 卖掉了 " + ticket + " 号票");
                    ticket--;
                } else break;
            }
        }
    }

    public synchronized void sale_1() {
        System.out.println(this);
            if (ticket > 0) {
                //同步之后就不要再使用sleep，否则线程只会霸占资源后，进行无意义的睡眠
                System.out.println(Thread.currentThread().getName() + " 卖掉了 " + ticket + " 号票");
                ticket--;
            }else exit(0);
        }
}

