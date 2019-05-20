# 第2讲 | Exception和Error有什么区别？

### 链接

* [第2讲 | Exception和Error有什么区别？](https://time.geekbang.org/column/article/6849)



## 问题：请对比 Exception 和 Error，另外，运行时异常与一般异常有什么区别

* 原文答案

Exception和Error都是继承了Throwable类，在java中只有Throwable类型的实例才可以被抛出thow或者捕获catch，他是异常处理机制的基本组成类型。

Exception和Error体系了Java平台设计者对不同异常情况的分类。Exception是程序正在运行中，可以预料的意外情况，并且应该被捕获，做出响应的处理。

Error是指在正常情况下，不大可能出现的情况，绝大部分的Error都会导致程序处于非正常，不可恢复的状态。所以不方便也不需要进行捕获。常见的OutOfMemoryError就是Error的子类。

Exception 又分为可检查（checked）异常和不检查（unchecked）异常，可检查异常在源代码里必须显式地进行捕获处理，这是编译期检查的一部分。前面我介绍的不可查的 Error，是 Throwable 不是 Exception。

不检查异常就是所谓的运行时异常，类似 NullPointerException、ArrayIndexOutOfBoundsException 之类，通常是可以编码避免的逻辑错误，具体根据需要来判断是否需要捕获，并不会在编译期强制要求。

* 自己答案

## 问题:NoClassDefFoundError与ClassNotFoundException之间的区别

ClassNotFoundException出现异常的情形,调用以下方法但是并没有找到指定名称类的定义

* Class类中的forName()方法
* ClassLoader类中的findSystemClass方法
* ClassLoader类中的loadClass方法

NoClassDefFoundError

* 如果jvm或者一个ClassLoader的实例尝试去载入一个class的定义内容（普通方法的调用或者用new去创建这个class的实例）但是没有找到这个class的信息。



