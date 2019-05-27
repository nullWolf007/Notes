# 第7讲 | int和Integer有什么区别？

### 链接

* [第7讲 | int和Integer有什么区别？](https://time.geekbang.org/column/article/7514)

## 问题：int和Integer有什么区别？谈谈Integer的值缓存范围。

### 原文回答

int是原始数据类型，java八个原始数据类型分别是byte,boolean,int,long,float,double,chat,short。Java语言虽然号称一切都是对象，但是原始数据类型除外。

Integer是int对应的包装类，它有一个int类型的字段存储数据，并且提供了基本操作，比如数学运算、int和字符串之间转换等。在Java5中，引入了自动装箱和自动拆箱功能(boxing/unboxing)，Java可以根据上下文，自动进行转换，极大地简化了相关编程。

<p>关于 Integer 的值缓存，这涉及 Java 5 中另一个改进。构建 Integer 对象的传统方式是直接调用构造器，直接 new 一个对象。但是根据实践，我们发现大部分数据操作都是集中在有限的、较小的数值范围，因而，在 Java 5 中新增了静态工厂方法 valueOf，在调用它的时候会利用一个缓存机制，带来了明显的性能改进。按照 Javadoc，<strong>这个值默认缓存是 -128 到 127 之间。</strong></p>