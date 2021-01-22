package pc.sync;

public class Deal {
    public static void main(String[] args) {
        Data data = new Data();

        //Producers
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        //Consumers
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    data.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }
}
class Data {
    private int number=0;

    public synchronized void produce() throws InterruptedException {
        //wait方法一定要放在while中,防止虚假唤醒
        while(number!=0){
            this.wait();
        }
        number++;
        System.out.println("Producer "+Thread.currentThread().getName()+" => "+number);
        this.notifyAll();
    }

    public synchronized void consume() throws InterruptedException {
        while(number==0){
            this.wait();
        }
        number--;
        System.out.println("Consumer "+Thread.currentThread().getName()+" => "+number);
        this.notifyAll();
    }
}