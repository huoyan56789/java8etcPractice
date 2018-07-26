package com.example.demo.java8;

/**
 * Created by chenyl on 2018/7/25.
 */
public class Test {
    public static void main(String[] args) {
        Lambda4 lambda4=new Lambda4();
        lambda4.testScopes();
    }

    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }

    static class Lambda4 {
        static int outerStaticNum=33;
        int outerNum=22;
        void testScopes() {
            Converter<Integer, String> stringConverter1 = (from) -> {
//                outerNum = 23;
                return String.valueOf(outerNum);
            };
            String a=stringConverter1.convert(outerNum);
            System.out.println(a);//输出23
            Converter<Integer, String> stringConverter2 = (from) -> {
//                outerStaticNum = 72;
                return String.valueOf(outerStaticNum);
            };
            String b=stringConverter2.convert(outerStaticNum);
            System.out.println(b);//输出23
        }
    }

    //这里的意思是outerStaticNum与outerNum两个参数可以多次赋值

}
