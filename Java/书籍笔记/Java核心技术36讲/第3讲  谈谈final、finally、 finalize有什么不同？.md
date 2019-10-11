# 第3讲 | 谈谈final、finally、 finalize有什么不同？

### 链接

* [第3讲 | 谈谈final、finally、 finalize有什么不同？](https://time.geekbang.org/column/article/6906)

## 问题:谈谈final,finally,finalize有什么不同?

final是一个用来修饰类，方法，变量的。

* final修饰类：用final修饰的类无法被继承
* final修饰方法：表示该方法不能被覆盖重写的。同时private方法都隐式的指定为final
* final修饰基本类型变量，变量不可以修改
* final修饰引用类型变量，这个变量的地址不会改变，但是如果是一个对象，这个对象里的值是可以改变的

finally是处理异常的时候用到的.可以保证这部分的代码一定会被执行,常见的有try-finally和try-catch-finally

finalize是基础类Object的一个方法，它的设计目的是保证对象在被垃圾回收前完成特定资源的回收，finalize机制现在已经不推荐使用，并且在JDK9被标记为deprecated

