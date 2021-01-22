package TicketSale;

public class TicketSaleWithDanger {
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

