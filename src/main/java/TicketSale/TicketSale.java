package TicketSale;

public class TicketSale {
    public static void main(String[] args) {
        Ticket ticket=new Ticket();
        new Thread(()->{ticket.sale();},"A").start();
        new Thread(()->{ticket.sale();},"B").start();
        new Thread(()->{ticket.sale();},"C").start();
    }
}
class Ticket{
    private int ticket=20;

    public void sale(){
        while(true){
            if(ticket>0) {
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println(Thread.currentThread().getName()+" "+ticket);
                ticket--;
            }
            else break;
        }
    }
}

