package com.wuminghui.JUC;


/**
 * @autor huihui
 * @date 2020/11/20 - 19:25  模拟线程间的通信(使用while解决虚假唤醒)
 */
class ShareDataDemo {
    private int num = 0;

    public synchronized void increment() throws InterruptedException {
        while (num != 0)
            this.wait();
        num++;
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        while (num != 1)
            this.wait();
        num--;
        System.out.println(Thread.currentThread().getName() + "\t" + num);
        this.notifyAll();
    }
}

public class Test02 {

    public static void main(String[] args) {
        ShareDataDemo shareDataDemo = new ShareDataDemo();
        new Thread(()->{
            try {
                for (int i = 1; i < 10; i++) {
                    shareDataDemo.increment();
                }
            }catch (Exception e){
               e.printStackTrace();
            }
        },"A").start();
        new Thread(()->{
            try {
                for (int i = 1; i < 10; i++) {
                    shareDataDemo.decrement();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        },"B").start();
        new Thread(()->{
            try {
                for (int i = 1; i < 10; i++) {
                    shareDataDemo.increment();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        },"C").start();
        new Thread(()->{
            try {
                for (int i = 1; i < 10; i++) {
                    shareDataDemo.decrement();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        },"D").start();


    }
}
