package com.wuminghui.JUC;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @autor huihui
 * @date 2020/11/20 - 19:03  模拟售票
 */
class saleTickes {
    private int tickes = 100;
    private final ReentrantLock lock = new ReentrantLock();

    public void sale() {
        lock.lock();

        try {
            while (tickes > 0) {
                System.out.println("抢走了第" + tickes-- + "张票");
            }
        } finally {
            lock.unlock();
        }

    }
}

public class Test01 {

    public static void main(String[] args) {
        saleTickes tickes = new saleTickes();
        new Thread(() -> {
            tickes.sale();
        }).start();
        new Thread(() -> {
            tickes.sale();
        }).start();

    }
}
