package jmm;

import java.util.concurrent.TimeUnit;

public class InvisibilityDemo {
    private static int num=0;
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{while(num==0);}).start();

        TimeUnit.MILLISECONDS.sleep(1);

        num=1;

    }
}
