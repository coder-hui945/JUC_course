package com.wuminghui.JUC;

import java.util.concurrent.CompletableFuture;

public class Test08_CompletableFutureDemo {

    public static void main(String[] args) throws Exception {
        //同步，异步，异步回调（asynchronize：异步）

//        CompletableFuture<Void> c
        //异步调用 无返回值ompletableFuture1 = CompletableFuture.runAsync(()->{
//            System.out.println(Thread.currentThread().getName()+"\t completableFuture1");
//        });
//        completableFuture1.get();

        //异步回调
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"\t completableFuture2");
//            int i = 10/0;
            return 1024;
        });

        System.out.println(completableFuture2.whenComplete((t, u) -> {
            System.out.println("-------t=" + t);//接收运算返回结果
            System.out.println("-------u=" + u);//接收异常
        }).exceptionally(f -> {
            System.out.println("-----exception:" + f.getMessage());
            return 444;
        }).get());
    }

}
 
 
