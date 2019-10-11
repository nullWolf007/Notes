[TOC]

# 第1讲 | 谈谈你对Java平台的理解？“Java 是解释执行”，这句话正确吗？

极客时间版权所有: https://time.geekbang.org/column/article/6845

### 链接

* [第1讲 | 谈谈你对Java平台的理解？](https://time.geekbang.org/column/article/6845)

### 前提知识

- [JIT（just-in-time）即时编译](https://github.com/nullWolf007/Notes/blob/master/Java/Java%E6%A0%B8%E5%BF%83%E6%8A%80%E6%9C%AF36%E8%AE%B2/%E8%A1%8D%E7%94%9F/JIT%E5%8D%B3%E6%97%B6%E7%BC%96%E8%AF%91.md)



Java平台有两大特性，一个是垃圾回收机制garbage collection，一个是write once，run anywhere。

1.1 java的write once，run anywhere得益于JVM，javac会把java源代码编译成java字节码，即.class文件。对于java字节码，不同的平台有不同的JVM，在不同的平台都可以完美的把java字节码转换成该平台的机器指令码，因此实现了书写一次，到处运行。

1.2 大部分JVM都依赖于JIT，JIT即just in time，能够对于热点代码进行以method为单位一次性的编译执行成机器指令码。并且保存下来，大大的提高了效率。而对于非热点代码，JVM采用的是解释执行。所以Java是解释执行这句话不完全正确。java中既包括解释执行，又包括编译执行。

