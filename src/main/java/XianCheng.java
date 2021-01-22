class Xc implements Runnable{

    public int chepiao = 20;
    public void run(){
        while (true) {
            if(chepiao>0){
                System.out.println("第" + Thread.currentThread().getName() + "个车站正在卖出第" + (21-chepiao--) + "张车票");
            }else{
                break;
            }
        }
    }
}
public class XianCheng {
    public static void main(String [] args){
        Xc xc =new Xc();
        Thread Xc1 = new Thread(xc,"A");//模拟两个车站在卖车票，竞争共同的线程资源
        Xc1.start();
        Thread Xc2 = new Thread(xc,"B");//模拟两个车站在卖车票，竞争共同的线程资源
        Xc2.start();
    }
}
