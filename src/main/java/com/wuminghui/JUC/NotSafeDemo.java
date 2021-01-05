package com.wuminghui.JUC;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 请举例说明集合类是不安全的
 */
public class NotSafeDemo {
    public static void main(String[] args) {

        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i <30 ; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }


    }

    private static void setNoSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i <30 ; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

    private static void listNoSafe() {
        //        List<String> list = Arrays.asList("a","b","c");
        //        list.forEach(System.out::println);
        //写时复制
        List<String> list = new CopyOnWriteArrayList<>();
        // new CopyOnWriteArrayList<>();
        //Collections.synchronizedList(new ArrayList<>());
        //new Vector<>();//new ArrayList<>();

        for (int i = 0; i <30 ; i++) {
                    new Thread(()->{
                        list.add(UUID.randomUUID().toString().substring(0,8));
                        System.out.println(list);
                    },String.valueOf(i)).start();
                }
    }


}





    /**
     * 写时复制
     CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，
     而是先将当前容器Object[]进行Copy，复制出一个新的容器Object[] newElements，然后向新的容器Object[] newElements里添加元素。
     添加元素后，再将原容器的引用指向新的容器setArray(newElements)。
     这样做的好处是可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。
     所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。

     *
     *
     *
     *

    public boolean add(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            setArray(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }
     */
 
 
