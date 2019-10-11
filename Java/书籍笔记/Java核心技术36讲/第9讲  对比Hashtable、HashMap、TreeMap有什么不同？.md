# 第9讲 | 对比Hashtable、HashMap、TreeMap有什么不同？

### 链接

* [第9讲 | 对比Hashtable、HashMap、TreeMap有什么不同？](https://time.geekbang.org/column/article/8053)

## 问题：对比Hashtable、HashMap、TreeMap有什么不同？

### 原文回答

<p>Hashtable、HashMap、TreeMap 都是最常见的一些 Map 实现，是以<strong>键值对</strong>的形式存储和操作数据的容器类型。</p>

<p>Hashtable 是早期 Java 类库提供的一个<a href="https://zh.wikipedia.org/wiki/%E5%93%88%E5%B8%8C%E8%A1%A8">哈希表</a>实现，本身是同步的，不支持 null 键和值，由于同步导致的性能开销，所以已经很少被推荐使用。</p>

<p>HashMap 是应用更加广泛的哈希表实现，行为上大致上与 HashTable 一致，主要区别在于 HashMap 不是同步的，支持 null 键和值等。通常情况下，HashMap 进行 put 或者 get 操作，可以达到常数时间的性能，所以<strong>它是绝大部分利用键值对存取场景的首选</strong>，比如，实现一个用户 ID 和用户信息对应的运行时存储结构。</p>

<p>TreeMap 则是基于红黑树的一种提供顺序访问的 Map，和 HashMap 不同，它的 get、put、remove 之类操作都是 O（log(n)）的时间复杂度，具体顺序可以由指定的 Comparator 来决定，或者根据键的自然顺序来判断。</p>

