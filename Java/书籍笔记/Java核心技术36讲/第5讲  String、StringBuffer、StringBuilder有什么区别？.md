# 第5讲 | String、StringBuffer、StringBuilder有什么区别？

### 参考

* [第5讲 | String、StringBuffer、StringBuilder有什么区别？](https://time.geekbang.org/column/article/7349)

## 问题：理解 Java 的字符串，String、StringBuffer、StringBuilder有什么区别？

* 原文答案

1. String是Immutable类的典型实现，被声明为final class，所有属性也都是final的。由于它的不可变性，类似拼接、裁剪字符串等动作，都会产生新的String对象。由于字符串操作的普遍性，所以相关操作的效率往往对应用性能有明显影响。
2. StringBuffer是为解决上面提到拼接产生太多中间对象的问题而提供的一个类，我们可以用append或者add方法，把字符串添加到已有序列的末尾或者指定位置。StringBuffer本质是一个线程安全的可修改字符序列，他保证了线程安全，也随之带来了额外的性能开销，所以除非有线程安全的需要，不然还是推荐使用StringBuilder
3. StringBuilder和StringBuffer在功能上没什么区别。但是StringBuilder不是线程安全的，有效的减少了开销，常用StringBuilder

