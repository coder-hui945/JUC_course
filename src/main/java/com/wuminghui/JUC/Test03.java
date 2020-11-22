package com.wuminghui.JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @autor huihui
 * @date 2020/11/20 - 19:43  线程通信，lock实现精准唤醒。
 */
class ShareResourse{
    private int number = 1;//A = 1  B = 2; C = 3;
    private  Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();


    public void print5(){
        lock.lock();
        try {
            while (number != 1){
                condition1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number ++;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void print10(){
        lock.lock();
        try {
            while (number != 2){
                condition2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number ++;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void print15(){
        lock.lock();
        try {
            while (number != 3){
                condition3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
public class Test03 {
    public static void main(String[] args) {
        ShareResourse shareResourse = new ShareResourse();

        new Thread(()->{
            for (int i = 0; i < 3; i++) {

                shareResourse.print5();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 3; i++) {

                shareResourse.print10();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 3; i++) {

                shareResourse.print15();
            }
        },"C").start();
    }
}
