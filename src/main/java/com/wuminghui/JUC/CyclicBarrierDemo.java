package com.wuminghui.JUC;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.FutureTask;

/**
 * @autor huihui
 * @date 2021/1/5 - 20:20
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("龙珠集齐，召唤神龙！");
        });
        for (int i = 1; i <= 7; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName()+"\t 被收集！");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
