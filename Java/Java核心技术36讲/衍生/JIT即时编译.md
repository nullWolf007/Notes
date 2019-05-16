[TOC]

# JIT（just-in-time）即时编译

### 参考

* [JIT（just-in-time）即时编译](https://blog.csdn.net/c1481118216/article/details/76592289)
* [深入浅出 JIT 编译器](https://www.ibm.com/developerworks/cn/java/j-lo-just-in-time/index.html)

## 一、基础知识

### 1.1 为什么需要JIT？

```text
对于java程序，首先通过javac将程序源代码编译，转换成java字节码；
然后利用JVM将解释字节码翻译成对应的机器指令，逐条读入，逐条解释翻译。
由于这个机制，其执行速度必然会比可执行的二进制字节码程序慢很多。为了提高执行速度，所以引入了JIT技术。			
```

### 1.2 JIT介绍

```text
JIT即just-in-time的缩写，也就是即时编译器。使用即时编译器技术，能够加速Java程序的执行速度。
JIT的执行对象是热点代码（Hot Spot Code）,对热点代码的java字节码直接编译成机器指令执行以提高性能，
同时会保存下来，方便二次执行。
```

### 1.3 什么是热点代码（Hot Spot Code）

```text
如果一段代码频繁的调用方法，或是循环，即这段代码被多次执行
1.被多次调用的方法
2.被多次调用的循环体
```

### 1.4 JVM和JNI的关系

```text
JVM提供JNI编译器
```

### 1.5解释执行和 编译执行

```text
解释执行：将编译好的字节码逐条地翻译成机器码执行
编译执行：以方法method为单位，将字节码一次性翻译成机器码执行
```

## 二、JIT编译过程

### 2.1 原理图

![](https://github.com/nullWolf007/images/raw/master/Java/Java36/jit_001.png)

```text
首先将Java代码通过javac编译成java字节码Byte Code。
然后判断Byte Code是不是热点代码，
如果是热点代码，则通过JIT进行一次性编译成机器指令Machine Code；
如果不是热点代码，则逐条进行解释成机器指令。
所以java即存在解释执行，又存在编译执行。
```

