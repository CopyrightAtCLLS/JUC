package SynchronizedDemo;

public class InsecureTicketSale {
    public static void main(String[] args) {
        InsecureTicket insecureTicket =new InsecureTicket();
        new Thread(()->{
            insecureTicket.sale();},"A").start();
        new Thread(()->{
            insecureTicket.sale();},"B").start();
        new Thread(()->{
            insecureTicket.sale();},"C").start();
    }
}
class InsecureTicket {
    private int ticket=5;

    public void sale(){
        while(true){
            if(ticket>0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" 卖掉了 "+ticket+" 号票");
                ticket--;
            }
            else break;
        }
    }
}

