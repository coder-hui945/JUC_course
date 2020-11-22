package com.wuminghui.JUC;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @autor huihui
 * @date 2020/11/21 - 14:23
 */
public class Test05_functionInterface {
    public static void main(String[] args) {
        Function<String,Integer> function1 = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };
        System.out.println(function1.apply("abcd"));
        System.out.println("=============================");

        Function<String,Integer> function2 = (s) ->{return s.length();};
        System.out.println(function2.apply("abcdef"));

        System.out.println("=============================");
//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return false;
//            }
//        };
        Predicate<String> predicate = (s) ->{return s.isEmpty();};
        System.out.println(predicate.test("abcdef"));
        System.out.println("=============================");


//        Consumer<String> consumer = new Consumer<String>() {
////            @Override
////            public void accept(String s) {
////
////            }
////        }

        Consumer<String> consumer = (s)->{
            System.out.println(s);
        } ;
        consumer.accept("sssss");

        System.out.println("=============================");


//        Supplier<String> supplier = new Supplier<String>() {
//            @Override
//            public String get() {
//                return null;
//            }
//        }

        Supplier<String> supplier = ()->{return "hello world!";};
        System.out.println(supplier.get());


    }
}
