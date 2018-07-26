package com.example.demo.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by chenyl on 2018/7/25.
 */
public class LambdaTest {
    public static void main(String[] args) {
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players =  Arrays.asList(atp);

// 以前的循环方式
        for (String player : players) {
            System.out.print(player + "; ");
        }

// 使用 lambda 表达式以及函数操作(functional operation)
        System.out.println("使用 lambda 表达式以及函数操作(functional operation)=====================");
        players.forEach((player) -> System.out.print(player + "; "));

// 在 Java 8 中使用双冒号操作符(double colon operator)
        System.out.println("在 Java 8 中使用双冒号操作符(double colon operator)=====================");

        players.forEach(System.out::println);

// 1.1 使用匿名内部类根据 name 排序 players
        System.out.println("1.1 使用匿名内部类根据 name 排序 players=====================");
        Arrays.sort(atp, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.compareTo(s2));
            }
        });

// 1.2 使用 lambda expression 排序 players
        System.out.println("1.2 使用 lambda expression 排序 players=====================");
        Comparator<String> sortByName = (String s1, String s2) -> (s1.compareTo(s2));
        Arrays.sort(atp, sortByName);

// 1.3 也可以采用如下形式:
        System.out.println("1.3 也可以采用如下形式:=====================");
        Arrays.sort(atp, (String s1, String s2) -> (s1.compareTo(s2)));

    }

/*    // 使用匿名内部类
btn.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("Hello World!");
        }
    });

// 或者使用 lambda expression
btn.setOnAction(event -> System.out.println("Hello World!"));*/
}
