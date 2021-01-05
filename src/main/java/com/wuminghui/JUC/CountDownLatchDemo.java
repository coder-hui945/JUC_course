package com.wuminghui.JUC;

import java.util.concurrent.CountDownLatch;

/**
 * @autor huihui
 * @date 2021/1/5 - 20:15
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i <=6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t班长关门走人！");
    }
}
