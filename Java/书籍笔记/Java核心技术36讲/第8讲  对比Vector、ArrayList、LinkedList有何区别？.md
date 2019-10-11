# 第8讲 | 对比Vector、ArrayList、LinkedList有何区别？

### 链接

* [第8讲 | 对比Vector、ArrayList、LinkedList有何区别？](https://time.geekbang.org/column/article/7810)

## 问题：对比Vector、ArrayList、LinkedList有何区别？

### 原文回答

<p>这三者都是实现集合框架中的 List，也就是所谓的有序集合，因此具体功能也比较近似，比如都提供按照位置进行定位、添加或者删除的操作，都提供迭代器以遍历其内容等。但因为具体的设计区别，在行为、性能、线程安全等方面，表现又有很大不同。</p>

<p>Vector 是 Java 早期提供的<strong>线程安全的动态数组</strong>，如果不需要线程安全，并不建议选择，毕竟同步是有额外开销的。Vector 内部是使用对象数组来保存数据，可以根据需要自动的增加容量，当数组已满时，会创建新的数组，并拷贝原有数组数据。</p>

<p>ArrayList 是应用更加广泛的<strong>动态数组</strong>实现，它本身不是线程安全的，所以性能要好很多。与 Vector 近似，ArrayList 也是可以根据需要调整容量，不过两者的调整逻辑有所区别，Vector 在扩容时会提高 1 倍，而 ArrayList 则是增加 50%。</p>

<p>LinkedList 顾名思义是 Java 提供的<strong>双向链表</strong>，所以它不需要像上面两种那样调整容量，它也不是线程安全的。</p>

### 自己回答

Vector是线程安全的，也就意味着需要额外的开销去维护线程安全，同时会降低速度。因此如果没有特别的线程安全的要求的话，不建议使用Vector

ArrayList和LinkedList都是线程不安全的，速度快。

ArrayList是基于数组实现的，因此ArrayList方便进行查找，但是不利于增删（除去尾部）

LinkedList是基于链表的，因此LinkedList方便进行增删，不利于查找