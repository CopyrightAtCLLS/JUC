package TicketSale;

public class TicketSaleWithDanger {
    public static void main(String[] args) {
        Ticket ticket=new Ticket();
        new Thread(()->{ticket.sale();},"A").start();
        new Thread(()->{ticket.sale();},"B").start();
        new Thread(()->{ticket.sale();},"C").start();
    }
}
class Ticket{
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

