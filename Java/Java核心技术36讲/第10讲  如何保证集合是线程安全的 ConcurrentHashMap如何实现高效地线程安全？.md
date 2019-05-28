[TOC]

# 第10讲 | 如何保证集合是线程安全的? ConcurrentHashMap如何实现高效地线程安全？

### 链接

* [第10讲 | 如何保证集合是线程安全的? ConcurrentHashMap如何实现高效地线程安全？](https://time.geekbang.org/column/article/8137)

## 问题：如何保证集合是线程安全的? ConcurrentHashMap如何实现高效地线程安全？

### 原文回答

Java提供了不同层面的线程安全支持。在传统集合框架内部，除了HashTable等同步容器，还提供了所谓的同步包装器（Synchronized Wrapper），我们可以调用Collections工具类提供的包装方法，来获取一个同步的包装容器（如Collections.synchronizedMap），但是它们都是利用非常粗粒度的同步方式，在高并发情况下，性能比较低下。

另外，更加普遍的选择是利用并发包提供的线程安全容器类，提供了：

1. 各种并发容器，比如ConcurrentHashMap、CopyOnWriteArrayList
2. 各种线程安全队列（Queue/Deque），如ArrayBlockingQueue、SynchronousQueue
3. 各种有序容器的线程安全版本等

具体保证线程安全的方式，包括有从简单的synchronize方式，到基于更加精细化的，比如基于分离锁实现的ConcurrentHashMap等并发实现等。具体选择要看开发的场景需求，总体来说，并发包内提供的容器通用场景，远优于早期的简单同步实现