package com.example.demo.java8;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Predicate即判断输入的对象是否符合某个条件。
 * Consumer接口
 即接口表示一个接受单个输入参数并且没有返回值的操作。不像其他函数式接口，
 Consumer接口期望执行带有副作用的操作（译者注：Consumer的操作可能会更改输入参数的内部状态）。
 * Created by chenyl on 2018/7/25.
 */
public class PreidcateConsumerDemo {


//    Consumer接口中有2个方法，有且只有一个声明为accept(T t)的方法，接收一个输入参数并且没有返回值。
// 为了详细说明Predicate和Consumer接口，我们来考虑一下学生的例子：Student类包含姓名，分数以及待付费用，
// 每个学生可根据分数获得不同程度的费用折扣。我们分别声明一个接受Student对象的Predicate接口以及Consumer接口的实现类。
// 如果你还不熟悉Function接口，那么你需要花几分钟阅读一下这篇文章。这个例子使用Predicate接口实现类的test()方法
// 判断输入的Student对象是否拥有费用打折的资格，然后使用Consumer接口的实现类更新输入的Student对象的折扣。
    public static Student updateStudentFee(Student student, Predicate<Student> predicate, Consumer<Student> consumer) {

        //Use the predicate to decide when to update the discount.
        if (predicate.test(student)) {
            //Use the consumer to update the discount value.
            consumer.accept(student);
        }
        return student;

    }

//    Predicate和Consumer接口的test()和accept()方法都接受一个泛型参数。不同的是test()方法进行某些逻辑判断并返回一个boolean值，
// 而accept()接受并改变某个对象的内部值。updateStudentFee方法的调用如下所示
    public static void main(String[] args) {

        Student student1 = new Student("Ashok","Kumar", 9.5);
        student1 = updateStudentFee(student1,

                //Lambda expression for Predicate interface
                student -> student.grade > 8,
                //Lambda expression for Consumer inerface
                student -> student.feeDiscount = 30.0);

        student1.printFee();
        Student student2 = new Student("Rajat","Verma", 7.0);
        student2 = updateStudentFee(student2,
                student -> student.grade >= 8,
                student -> student.feeDiscount = 20.0);
        student2.printFee();

    }

}
class Student {


    String firstName;
    String lastName;
    Double grade;
    Double feeDiscount = 0.0;
    Double baseFee = 20000.0;

    public Student(String firstName, String lastName, Double grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
    }

    public void printFee() {
        Double newFee = baseFee - ((baseFee * feeDiscount) / 100);
        System.out.println("The fee after discount: " + newFee);
    }

}