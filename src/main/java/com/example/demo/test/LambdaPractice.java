package com.example.demo.test;

/**
 * Created by chenyl on 2018/7/25.
 */
public class LambdaPractice {
    public static void main(String[] args) {

        LambdaPractice tester = new LambdaPractice();
        MathOperation add = (a,b)->a+b;
        System.out.println(tester.operate(1,2,add));
        }
    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
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
