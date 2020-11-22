package com.wuminghui.JUC;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.util.concurrent.*;

/**
 * @autor huihui
 * @date 2020/11/21 - 9:35
 */
public class Test04_ThredPool {
    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());//查看电脑线程数---专业！
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        ExecutorService threadPool1 = Executors.newSingleThreadExecutor();//单池单线程
//        ExecutorService threadPool2 = Executors.newCachedThreadPool();//可扩容,可伸缩，一池N线程。（线程运行快开辟线程多，慢少）
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,5,2L,TimeUnit.SECONDS,new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardPolicy());
        try {
            for (int i = 0; i < 20; i++) {
                 threadPool.execute(()->{
                     System.out.println(Thread.currentThread().getName()+"办理业务");
                 });
//                 try {
//                     TimeUnit.MILLISECONDS.sleep(500);
//                 }catch (Exception e){
//                     e.printStackTrace();
//                 }
            }
        }catch (Exception e){
                    e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
