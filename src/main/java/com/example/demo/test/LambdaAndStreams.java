package com.example.demo.test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Lambda表达式还增强了集合库。 Java SE 8添加了2个对集合数据进行批量操作的包:
 * java.util.function 包以及java.util.stream 包。 流(stream)就如同迭代器(iterator),
 * 但附加了许多额外的功能。 总的来说,lambda表达式和 stream 是自Java语言添加泛型(Generics)
 * 和注解(annotation)以来最大的变化。
 * 在本文中,我们将从简单到复杂的示例中见认识lambda表达式和stream的强悍。
 * Created by chenyl on 2018/7/25.
 */
public class LambdaAndStreams {
    public static void main(String[] args) {
        List<Person> javaProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
            }
        };

        List<Person> phpProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
                add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
                add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
                add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
                add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
                add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
                add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
                add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
                add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
                add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
            }
        };
//        使用forEach方法来迭代输出上述列表:
        System.out.println("所有程序员的姓" + "名:");
        javaProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
        phpProgrammers.forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));

        System.out.println("给程序员加薪 5% :");
        Consumer<Person> giveRaise = e -> e.setSalary(e.getSalary() / 100 * 5 + e.getSalary());
       javaProgrammers.forEach(giveRaise);
        phpProgrammers.forEach(giveRaise);
//        方法是过滤器filter() ,让我们显示月薪超过1400美元的PHP程序员:
        System.out.println("下面是月薪超过 $1,400 的PHP程序员:");
        phpProgrammers.stream()
                .filter((p) -> (p.getSalary() > 1400))
                .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));

        // 定义 filters
        Predicate<Person> ageFilter = (p) -> (p.getAge() > 25);
        Predicate<Person> salaryFilter = (p) -> (p.getSalary() > 1400);
        Predicate<Person> genderFilter = (p) -> ("female".equals(p.getGender()));

        System.out.println("下面是年龄大于 24岁且月薪在$1,400以上的女PHP程序员:");
        phpProgrammers.stream()
                .filter(ageFilter)
                .filter(salaryFilter)
                .filter(genderFilter)
                .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));

// 重用filters
        System.out.println("年龄大于 24岁的女性 Java programmers:");
        javaProgrammers.stream()
                .filter(ageFilter)
                .filter(genderFilter)
                .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
//        使用limit方法,可以限制结果集的个数:
        System.out.println("最前面的3个 Java programmers:");
        javaProgrammers.stream()
                .limit(3)
                .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));


        System.out.println("最前面的3个女性 Java programmers:");
        javaProgrammers.stream()
                .filter(genderFilter)
                .limit(3)
                .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
//        在下面的例子中,我们将根据名字和薪水排序Java程序员,放到一个list中,然后显示列表:
        System.out.println("根据 name 排序,并显示前5个 Java programmers:");
        List<Person> sortedJavaProgrammers = javaProgrammers
                .stream()
                .sorted((p, p2) -> (p.getFirstName().compareTo(p2.getFirstName())))
                .limit(5)
                .collect(toList());
//        Stream并没有new一个新List。我们要做的事情就是，获取到Stream，告诉Stream我们要做什么，
// 然后问Stream要结果。那要是我们不问Stream要结果，那会怎么样，答案是Stream绝对什么都不做。
        System.out.println("java女人数" + javaProgrammers.stream().filter(person->person.getGender().equals("female")).count());
        sortedJavaProgrammers.forEach((p) -> System.out.printf("%s %s; %n", p.getFirstName(), p.getLastName()));

        System.out.println("根据 salary 排序 Java programmers:");
        sortedJavaProgrammers = javaProgrammers
                .stream()
                .sorted( (p, p2) -> (p.getSalary() - p2.getSalary()) )
                .collect( toList() );

        sortedJavaProgrammers.forEach((p) -> System.out.printf("%s %s; %n", p.getFirstName(), p.getLastName()));
//        如果我们只对最低和最高的薪水感兴趣,比排序后选择第一个/最后一个 更快的是min和max方法:
        System.out.println("工资最低的 Java programmer:");
        Person pers = javaProgrammers
                .stream()
                .min((p1, p2) -> (p1.getSalary() - p2.getSalary()))
                .get();

        System.out.printf("Name: %s %s; Salary: $%,d.", pers.getFirstName(), pers.getLastName(), pers.getSalary());

        System.out.println("工资最高的 Java programmer:");
        Person person = javaProgrammers
                .stream()
                .max((p, p2) -> (p.getSalary() - p2.getSalary()))
                .get();

        System.out.printf("Name: %s %s; Salary: $%,d.", person.getFirstName(), person.getLastName(), person.getSalary());
//        上面的例子中我们已经看到 collect 方法是如何工作的。
// 结合 map 方法,我们可以使用 collect 方法来将我们的结果集放到一个字符串,一个 Set 或一个TreeSet中:
        // 在 Java 8 中使用双冒号操作符(double colon operator)
        //[方法引用],这种[方法引用]或者说[双冒号运算]对应的参数类型是Function<T,R> T表示传入类型，R表示返回类型。
        System.out.println("将 PHP programmers 的 first name 拼接成字符串:");
        String phpDevelopers = phpProgrammers
                .stream()
                .map(Person::getFirstName)
                .collect(joining(" ; ")); // 在进一步的操作中可以作为标记(token)

        System.out.println(phpDevelopers);
        System.out.println("将 Java programmers 的 first name 存放到 Set:");
        Set<String> javaDevFirstName = javaProgrammers
                .stream()
                .map(Person::getFirstName)
                .collect(toSet());
        System.out.println(javaDevFirstName);

        System.out.println("将 Java programmers 的 first name 存放到 TreeSet:");
        TreeSet<String> javaDevLastName = javaProgrammers
                .stream()
                .map(Person::getLastName)
                .collect(toCollection(TreeSet::new));
        System.out.println("javaDevLastName" + javaDevLastName);
//        Streams 还可以是并行的(parallel)。 示例如下:
// mapToInt这里我们使用了mapToT方法，T表示基础数据类型，包括int long double，注意没有float哦。
// mapToT方法返回值是TStream类型，TStream类包含了一些处理基础数据的方法，可以让我们更方便。
// 我们使用mapToT的原因，不仅仅是方便，还在于性能。我们知道，因为泛型的原因，可以有List<Integer>但是不能有List<int>，
// 这里的IntStream就相当于是List<int>,int 所占内存比Integer小。根据上面两个例子，相信你能够自己推测出flatMapToInt()
// 这种flatMapToT()的用法和功能了吧，这里的T同样表示int long double基础数据类型。若你还是对MapToT方法感到困惑，不知道怎么来的，
// 那么，接下来我们就单独讲讲上面代码里Stream.mapToInt(person -> person.getAge())这一段，这里大有乾坤哦。
// Stream对mapToInt 的定义是这样的：就是一个省略了ToIntFunction的匿名函数接口，也就相当于是：


//https://blog.csdn.net/lsmsrc/article/details/41120127
//        ToIntFunction<Person> trans = person-> person.getAge();
//
//        Stream.mapToInt(trans)
        System.out.println("计算付给 Java programmers 的所有money:");
        int totalSalary = javaProgrammers
                .parallelStream()
                .mapToInt(p -> p.getSalary())
                .sum();
        System.out.println("totalSalary" + totalSalary);
//        我们可以使用summaryStatistics方法获得stream 中元素的各种汇总数据。
// 接下来,我们可以访问这些方法,比如getMax, getMin, getSum或getAverage:
//计算 count, min, max, sum, and average for numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        IntSummaryStatistics stats = numbers
                .stream()
                .mapToInt((x) -> x)
                .summaryStatistics();

        System.out.println("List中最大的数字 : " + stats.getMax());
        System.out.println("List中最小的数字 : " + stats.getMin());
        System.out.println("所有数字的总和   : " + stats.getSum());
        System.out.println("所有数字的平均值 : " + stats.getAverage());

        //注意这里的双参数，是加了括号的。仅一个参数时可以省略括号，其他参数个数不能省，包括空参数
        BinaryOperator<Integer> biopt = (x, y) -> x - y;
        System.out.println(biopt.apply(2, 4));
        System.out.println("biop========="+biopt.apply(2, 4));
        BinaryOperator<Integer> biopt1 = (x,y)->{//注意这里的方法块括号，仅一条语句时可以省略花括号
            if(x<y){
                return y-x;
            }
            return x-y;//注意，当传入方法块的时候，“return” 不能省略
        };
        System.out.println("biopt1========"+biopt1.apply(2, 4));


//        map的意思是，1对1转换。也就是说map方法执行后，得到的“新集合”跟原集合的size是一样大的，
// 而filter方法执行后得到的“子集合”可以比原集合的size小。我们用map来干什么，比如前面说过,对List<Integer>进行处理的时，会频繁地包装和解包。
// 这样消耗内存占用时钟。我们可以用map转换成List<int>类型再计算。关于这方面的，放在后面篇章里面说。

// https://blog.csdn.net/lsmsrc/article/details/41120127

            List<String> collected = new ArrayList<>();
            collected.add("alpha");
            collected.add("beta");
            collected.add("cool");
            collected.add("delta");
            collected = collected.stream().map(string -> string.toUpperCase())
                    .collect(Collectors.toList());


/**

 * 测试flatMap类似于addAll的功能
 flatMap可以一下子合并多个集合，并且，不会正真意义上new出新的集合出来，那么明显会占用更少的内存空间，若你要的就是合并后的新集合，
 那另当别论。这都是小儿科，若你想把List<Person>里面的Person里面的name和age单独放入一个集合内，这时选择flatMap来完成，也是极好的。
 */

            List<Integer> collected0 = new ArrayList<>();

            collected0.add(1);
            collected0.add(3);
            collected0.add(5);
            List<Integer> collected1 = new ArrayList<>();
            collected1.add(2);
            collected1.add(4);
            collected1 = Stream.of(collected0, collected1)
                    .flatMap(num -> num.stream()).collect(Collectors.toList());
            System.out.println(collected1);// 1,3,5,2,4

//        Stream.reduce()介绍了过滤，转换，合并之后，再看一个，遍历操作临近元素的例子，比如现在有个放有整数的List，
// 我们要得到其总和，那么我们可以用下面的代码来实现：遍历操作临近元素的例子，
        int sumAll = Stream.of(1, 2, 3, 4).reduce(0, (sum, element) -> sum + element);// 给一个0是用来启动，的，若给-1，结果会是9
        System.out.println(sumAll);// 10

        Optional<Integer> sumAll1 = Stream.of(1, 2, 3, 4).reduce(Integer::sum);// 注意返回值类型
        System.out.println(sumAll1);// Optional[10]     注意输出值不再是10了


        Integer testInt[] = {};

        Optional<Integer> sumAll2 = Stream.of(testInt).reduce(Integer::sum);
        System.out.println(sumAll2);
        sumAll2.ifPresent(x -> {
            System.out.println(x); //sumAll不为空的时候，打印x的值；为空的时候，不做任何操作
        });
//        上面例子中我们可以给Optional一个默认值0值缺省(null)时会返回默认值，
// 但是这样写有个问题，那就是默认值是先new出来的，占有一定空间呀，有没有一种方式，缺省时再去new呢。
// 有那就是orElseGet(Supplier<? extends T>)，这段代码就可以改成下面这样：
        System.out.println(sumAll2.orElse(0));// 0
        System.out.println(sumAll2.orElseGet(() -> 0));
//        值出现缺省状态，在业务上就是错了，这个时候你很想抛个特定的异常出来，怎么弄呢。
// 你只需要调用orElseThrow方法就可以了，比如用.orElseThrow(() -> new Throwable("不能为空"))。

//        try {
//            System.out.println(sumAll2.orElseThrow(()-> new Throwable("不能为空")));
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
        System.out.println("33333333333333");
        Optional<List<Integer>> optional = Optional.of(new ArrayList<>());//of()里面不能传入null

        System.out.println(optional.get()); // []

        Optional<List<Integer>> optional1 = Optional.ofNullable(new ArrayList<>());

        System.out.println(optional1.get()); // []

        Optional<List<Integer>> emptyOptional = Optional.empty();

        Optional<List<Integer>> emptyOptional2 = Optional.ofNullable(null);

        System.out.println(emptyOptional.equals(emptyOptional2)); //true


        String testS[]={"hello"," ","world"," ","!"};

        Optional<String> s = Stream.of(testS).reduce(String::concat);

        System.out.println(s.map(x-> null));//Optional.empty

        System.out.println(s.map(x-> x.toUpperCase()));//Optional[HELLO WORLD !]

//        其实flatMap跟map的操作非常相似，但是呢，flatMap里面传入的东西必须是Optional对象。
// 我们看到map可以返回null可以返回x.toUpperCase()，但是flatMap是不可以的。上面的例子我们改由flat


        System.out.println(s.flatMap(x-> Optional.ofNullable(null)));//Optional.empty

        System.out.println(s.flatMap(x-> Optional.of(x.toUpperCase())));//Optional[HELLO WORLD !]
    }
}
