[TOC]

# Lambda表达式及方法引用

### 参考链接

* [**Java基础系列-Lambda**](https://www.jianshu.com/p/6c08fa953ce4)
* [Java8 lambda表达式10个示例](https://www.cnblogs.com/coprince/p/8692972.html)

## 一、前言

### 1.1 函数式编程

* JDK1.8引入了函数式编程，重点包括函数式接口，lambda表达式，方法引用等
* 函数式编程就是将函数（一段操作）作为一个基本单位进行传递，以前的Java中参数只能是具体的变量，函数式编程打破这一规范，可以将整个方法作为一个参数传递

### 1.2 函数式接口

* 函数式接口是JDK1.8中提出的新概念。
* 函数式编程就是只有一个抽象方法的接口。常用的函数式接口有Runnable，Comparator等。
* 但是由注解@FunctionalInteface标注的接口，统称为函数式接口，强制性的只有一个抽象方法。
* 为了函数是接口的扩展，JDK对接口规范进行了进一步修改，接口中除了可以自定义抽象方法之外，还能够定义静态方法和默认方法，而且这两种方法都可以拥有自己的实现。其中静态方法一般作为工具方法，而默认方法是可以被继承重写的，还能拥有一个默认的实现。除此之外，函数式接口还可以重写Object中定义的方法

* 示例

  ```java
  //自定义函数式接口
  @FunctionalInterface
  public interface ITest {
      void test();//抽象方法:public abstract省去了
  
      boolean equals(Object obj);// 重写Object中的方法
  
      default void defaultMethod() {
          // 这是一个默认方法
      }
  
      static void staticMethod() {
          // 这是一个静态方法
      }
  }
  ```

### 1.3 Lambda表达式

#### 1.3.1 优势

* Lambda表达式可以使代码变的更加简洁紧凑

#### 1.3.2 表达式语法

  ```java
  (parameters) -> expression
  或
  (parameters) -> {statements;}
  ```

#### 1.3.3 重要特性

* 可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。 
* 可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。 
* 可选的大括号：如果主体包含了一个语句，就不需要使用大括号。 
* 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。 

### 1.4 方法引用

* 可以把方法引用理解为更加紧凑的lambda表达式

* 方法引用出现的目的是为了解决所需的操作已经存在的情况。
* 当我们需要传递的操作已经存在，那就不必再费尽心思的再写一个出来啦，直接使用方法引用来将已有的方法给它就行了。
* 方法引用使用“::”双英文冒号组成的操作符来指定方法。

### 1.5 常用的预定义的函数式接口

| 序号 |        接口名         |         抽象方法          |                         说明                         |     备注     |
| :--: | :-------------------: | :-----------------------: | :--------------------------------------------------: | :----------: |
|  1   |    **Supplier<T>**    |        **T get()**        |    **无输入参数，通过一系列操作产生一个结果返回**    | **无中生有** |
|  2   |    **Consumer<T>**    |   **void accept(T t)**    |   **一个输入参数，针对参数做一系列操作，无返回值**   | **消费掉了** |
|  3   |  **BiConsumer<T,U>**  | **void accept(T t, U u**) |   **两个输入参数，针对参数做一系列操作，无返回值**   | **消费掉了** |
|  4   |   **Function<T,R>**   |     **R apply(T t)**      |   **一个参数，一个返回值，针对参数生成一个返回值**   | **一因一果** |
|  5   | **BiFunction<T,U,R>** |   **R apply(T t, U u)**   | **两个输入参数，一个返回值，根据参数生成一个返回值** | **多因一果** |
|  6   |   **Predicate<T>**    |   **boolean test(T t)**   |          **一个参数，返回校验boolean结果**           | **校验参数** |
|  7   | **UnaryOperator<T>**  |     **T apply(T t)**      |       **一个T型参数，通过操作返回一个T型结果**       | **一元操作** |
|  8   | **BinaryOperator<T>** |  **T apply(T t1, T t2)**  |       **两个T型参数，通过操作返回一个T型结果**       | **二元操作** |

## 二、Lambda表达式详解

### 2.1 替换匿名内部类(如Runnable)

* 使用lambda替换匿名内部类有一个前提，就是匿名内部类的接口类型必须是函数式接口。如果不是函数式接口则无法替换

* 示例代码

  ```java
  public class Test {
      public static void main(String[] args) {
          //jdk 1.8以前
          new Thread(new Runnable() {
              @Override
              public void run() {
                  System.out.println("JDK1.8以前的写法");
              }
          }).start();
  
          //jdk1.8及以后
          new Thread(() -> System.out.println("JDK1.8的写法")).start();
      }
  }
  ```

### 2.2 替换函数式接口

#### 2.2.1 OnClickListener

* 示例代码

  ```java
  public class Test {
      public static void main(String[] args) {
          //jdk 1.8以前
          textView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
  
              }
          });
  
  
          //jdk1.8及以后
          textView.setOnClickListener((View.OnClickListener) view -> {
              
          });
      }
  }
  ```

#### 2.2.2 filter之函数式接口Predicate

* 示例代码

  ```java
  public class Test {
      public static void main(String[] args) {
          //jdk 1.8以前
          List<String> original = Arrays.asList("abc", "bcd", "defg", "jk");
          List<String> filtered = new ArrayList<>();
  
          //最普通的
          filtered.clear();
          for (String str : original) {
              if (str.length() > 2) {
                  filtered.add(str);
              }
          }
          System.out.printf("Original: %s, filtered: %s %n", original, filtered);
  
          //stream
          filtered.clear();
          filtered = original.stream().filter(new Predicate<String>() {
              @Override
              public boolean test(String s) {
                  return s.length() > 2;
              }
          }).collect(Collectors.toList());
          System.out.printf("Original: %s, filtered: %s %n", original, filtered);
  
          //lambda
          filtered.clear();
          filtered = original.stream().filter(x -> x.length() > 2).collect(Collectors.toList());
          System.out.printf("Original: %s, filtered: %s %n", original, filtered);
      }
  }
  ```

* 合成Predicate示例

  ```java
  public class Test {
      public static void main(String[] args) {
          List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Jar");
  
          Predicate<String> startWithPre = (str) -> str.startsWith("J");
          Predicate<String> lengthPre = (str) -> str.length() > 3;
  
          languages.stream()
                  .filter(startWithPre.and(lengthPre))
                  .forEach(System.out::println);
      }
  }
  ```

#### 2.2.4 map之函数式接口Function

* 示例代码

  ```java
  public class Test {
      public static void main(String[] args) {
          //最常用的
          List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
          for (Integer cost : costBeforeTax) {
              double price = cost + 0.12 * cost;
              System.out.println(price);
          }
  
          //使用stream
          costBeforeTax.stream().map(new Function<Integer, Object>() {
              @Override
              public Object apply(Integer integer) {
                  return integer + 0.12 * integer;
              }
          }).forEach(System.out::println);
  
          //使用lambda表达式
          costBeforeTax.stream().map((cost) -> cost + 0.12 * cost).forEach(System.out::println);
      }
  }
  ```

#### 2.2.5 reduce之函数式接口BinaryOperator

* 实例代码

  ```java
  public class Test {
      public static void main(String[] args) {
          List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
          double total = 0;
          //最常用的
          for (Integer cost : costBeforeTax) {
              double price = cost + .12 * cost;
              total = total + price;
          }
          System.out.println("Total : " + total);
  
          //使用stream
          total = costBeforeTax.stream().map(new Function<Integer, Double>() {
              @Override
              public Double apply(Integer integer) {
                  return integer + 0.12 * integer;
              }
          }).reduce(new BinaryOperator<Double>() {
  
              @Override
              public Double apply(Double integer, Double integer2) {
                  return integer + integer2;
              }
          }).get();
          System.out.println("Total : " + total);
  
          //使用lambda表达式
          total = costBeforeTax.stream().map((cost) -> cost + .12 * cost).reduce((sum, cost) -> sum + cost).get();
          System.out.println("Total : " + total);
      }
  }
  ```

#### 2.2.6 forEach之函数式接口Consumer

* 示例代码

  ```java
  public class Test {
      public static void main(String[] args) {
          //jdk 1.8以前
          List<String> features = Arrays.asList("Lambdas", "Default Method");
          for (String feature : features) {
              System.out.println(feature);
          }
  
          //foreach
          features.forEach(new Consumer<String>() {
              @Override
              public void accept(String s) {
                  System.out.println(s);
              }
          });
  
          //lambda
          features.forEach(s -> System.out.println(s));
  
          //方法引用
          features.forEach(System.out::println);
      }
  }
  ```

## 三、方法引用详解

### 3.1 方法引用分类

* 对象引用::实例方法名
* 类名::静态方法名
* 类名::实例方法名
* 类名::new
* 类型[]::new

### 3.2 对象引用::实例方法名

* 实例代码

  ```java
  public class Test {
      public int compareByName(Person a, Person b) {
          return a.getName().compareTo(b.getName());
      }
  
      public static void main(String[] args) {
          Person[] pArr = new Person[]{
                  new Person("1", LocalDate.of(2019, 12, 1)),
                  new Person("3", LocalDate.of(2019, 12, 3)),
                  new Person("2", LocalDate.of(2019, 12, 2))
          };
          //lambda
          Arrays.sort(pArr, (a, b) -> a.getName().compareTo(b.getName()));
          //lambda更加紧凑--方法引用
          Test test = new Test();
          Arrays.sort(pArr, test::compareByName);
      }
  }
  ```

### 3.3 类名::静态方法名

* 实例代码

  ```java
  String::valueOf  等价于 s -> String.valueOf(s)
  System.out::println 等价于 s -> System.out.println(s)
  Math::pow 等价于 (x,y) -> Math.pow(x,y)
  ```

* 说明：String是一个类，而valueOf是该类的静态方法

### 3.4 类名::实例方法名

* 实例代码

  ```java
  BiPredicate<String, String> b = String::equals;
  System.out.println(b.test("abc", "abcd"));
  ```

* 说明：String是一个类，而equals为该类定义的实例方法。

### 3.5 类名::new

* 实例代码

  ```java
  public class Person {
      String name;
      LocalDate birthday;
  
      public Person() {
      }
  }
  
  public class PersonFactory {
      private Supplier<Person> supplier;
  
      public PersonFactory(Supplier<Person> supplier) {
          this.supplier = supplier;
      }
  
      public Person getPerson() {
          return supplier.get();
      }
  
      public static void main(String[] args) {
          PersonFactory factory = new PersonFactory(Person::new);
          Person p1 = factory.getPerson();
      }
  }
  ```

### 3.6 类型[]::new

* int[]::new 等价于 x -> new int[x]

* 实例代码

  ```java
  Function<Integer, int[]> fun = int[]::new;
  int[] arr = fun.apply(10);
  Function<Integer, Integer[]> fun2 = Integer[]::new;
  Integer[] arr2 = fun2.apply(10);
  ```

  













