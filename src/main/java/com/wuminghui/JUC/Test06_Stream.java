package com.wuminghui.JUC;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @autor huihui
 * @date 2020/11/21 - 14:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//在setting中plugin查找并安装lombok工具，并在pom中引入lombok依赖，方便类的各种方法的书写：通过注解代替各种方法的声明。
class User {
    private int id;
    private String userName;
    private int age;
}

public class Test06_Stream {
    public static void main(String[] args) {
        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(16, "e", 26);

        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);
//        list.stream().filter(t -> {return t.getId()%2 == 0;}).forEach(System.out::println);与下句等价

        list.stream().filter((u) -> {
            return u.getId() % 2 == 0;
        }).filter((u) -> {
            return u.getAge() > 24;
        }).map(u -> {
            return u.getUserName().toUpperCase();
        }).sorted((o1, o2) -> {
            return o2.compareTo(o1);
        }).limit(1).forEach((u) -> {
            System.out.println(u);
        });


    }
}
