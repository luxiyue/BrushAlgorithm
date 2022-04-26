package test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest {
    AtomicInteger a = new AtomicInteger(0);
    Integer b = 0;

    public static void main(String[] args) throws InterruptedException {
        aaaa();
    }

    private static void aaaa() throws InterruptedException {
        ThreadTest threadTest = new ThreadTest();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(()->{ for(int i=0;i<1000000;i++){ threadTest.a.incrementAndGet(); threadTest.b++;}countDownLatch.countDown(); },"A").start();
        new Thread(()->{ for(int i=0;i<1000000;i++){threadTest.a.decrementAndGet(); threadTest.b--;}countDownLatch.countDown(); },"B").start();
        countDownLatch.await();
        System.out.println(threadTest.a.get());
        System.out.println(threadTest.b);
    }
}
