## 关于GC的那些事儿
---

**什么是GC？**
* GC(Garbage Collection)也就是垃圾回收
---

**关于Garbage Collector**
1. Garbage Collector也就是垃圾回收站。在C++语言中，显式的用代码申请内存空间new和释放内存free，这样做的弊端是使用者容易忘记释放内存，造成内存问题，为此java引入了自动管理内存机制，这能解决绝大多数的问题，但有些情况不能解决就会造成内存泄漏。

2. Garbage Collector的三大职责：
* 分配内存
* 确保任何被引用的对象保留在内存中
* 回收不能通过引用关系找到的对象的内存
---

**Garbage Collection和Garbage Collector的关系**
* Garbage Collector上有一个进程完成上述的职责，这个进程就是Garbage Collection，也就是通常说的GC(垃圾回收)
---

**Heap内存和Stack内存**
<img src="https://github.com/nullWolf007/images/raw/master/Java/jvm/GC/heap-stack.png" width="640" height="320"/>

* Heap内存是指java运行环境用来分配给对象和JRE类的内存，是应用的内存
* Stack内存是相对于线程Thread而言的，它保存线程方法中短期存在的变量值和对Heap中对象的引用
* Stack内存，是类Stack方式的，也就是FILO
* GC是针对于Heap内存的，因为Stack内存是随用随销
---

**GC Roots**
1. 垃圾回收器不会回收GC Roots以及那些被它们间接引用的对象
2. GC Roots包含哪些对象呢？
* Class - 由系统类加载器(system class loader)加载的对象，这些类是不能够被回收的，他们可以以静态字段的方式保存持有其它对象。例如java运行环境中rt.jar中类， 比如java.util.* package中的类。我们需要注意的一点就是，通过用户自定义的类加载器加载的类，除非相应的java.lang.Class实例以其它的某种（或多种）方式成为roots，否则它们并不是roots。
* Thread - 活着的线程
* Stack Local - Java方法的local变量或参数
* JNI Local - JNI方法的local变量或参数
* JNI Global - 全局JNI引用
* Monitor Used - 用于同步的监控对象
* Held by JVM - 用于JVM特殊目的由GC保留的对象，但实际上这个与JVM的实现是有关的。可能已知的一些类型是：系统类加载器、一些JVM知道的重要的异常类、一些用于处理异常的预分配对象以及一些自定义的类加载器等。然而，JVM并没有为这些对象提供其它的信息，因此就只有留给分析分员去确定哪些是属于"JVM持有"的了。
---

**活对象/垃圾**
* 如果这个对象是引用可达的，就是活的；如果这个对象是不可达的，就是死的，也称作垃圾
* 这个引用的可达性是针对GC Root而言的

<img src="https://github.com/nullWolf007/images/raw/master/Java/jvm/GC/%E6%B4%BB%E5%AF%B9%E8%B1%A1-%E5%9E%83%E5%9C%BE.png" width="640" height="340"/>

---

**JVM内存区域**
* JVM使用分带式的内存管理方式，将Heap分成三代----新生代，老年代，持久代
  <img src="https://github.com/nullWolf007/images/raw/master/Java/jvm/GC/jvm%E5%86%85%E5%AD%98%E5%8C%BA%E5%9F%9F.png" width="600" height="300"/>
1. Young Generation
        
        ·新生代
        ·所有new的对象(除去大对象)
        ·该区域的内存管理使用Minor Garbage Collection(小GC)
        ·该部分进一步分成Eden space，Survivor 0，Survivor 1     

2. Old Generation
         
        ·老年区
        ·新生代中经过小GC幸存下来的对象
        ·该区域的内存管理使用Major Garbage Collection(大GC)

3. Permanent Generation
         
        ·持久代
        ·包括应用的类/方法信息，以及JRE库的类和方法信息

4. 小GC和大GC
* 小GC执行的非常频繁，速度非常快；大GC比小G慢十倍以上；大小GC都发出Stop The World，这样会终止程序的运行，直到GC完成，所以频繁的GC会导致用户感觉卡顿

**GC流程**
1. 当使用new创建新的对象时，这个对象就会被分配到Eden space区域

<img src="https://github.com/nullWolf007/images/raw/master/Java/jvm/GC/GC%E5%88%9B%E5%BB%BA%E6%96%B0%E7%9A%84%E5%AF%B9%E8%B1%A1.jpg" width="600" height="380"/>

2. 当Eden space满了的时候，小GC程序会被触发

<img src="https://github.com/nullWolf007/images/raw/master/Java/jvm/GC/GC%E5%8C%BA%E5%9F%9F%E6%BB%A1%E5%B0%8FGC%E8%A7%A6%E5%8F%91.png" width="600" height="340"/>

此时引用可达对象将会被移到S0区域，然后清空Eden区域，此时引用不可达对象将会被删除，内存回收

<img src="https://github.com/nullWolf007/images/raw/master/Java/jvm/GC/%E5%BC%95%E7%94%A8%E5%8F%AF%E8%BE%BE%E5%AF%B9%E8%B1%A1%E8%BD%AC%E7%A7%BB.png" width="720" height="320"/>

3. Eden再次满了，引用可达对象将会移到S1，清空Eden区域和S0区域，引用不可达对象将会被删除，内存回收；此时所有可达对象都在S1，并且有着不一样的年龄，经历过几次小GC，就有多大的年龄

<img src="https://github.com/nullWolf007/images/raw/master/Java/jvm/GC/Eden%E5%86%8D%E6%AC%A1%E6%BB%A1.png" width="600" height="320"/>

当Eden第三次满了，S0和S1的角色互换了

<img src="https://github.com/nullWolf007/images/raw/master/Java/jvm/GC/Eden%E7%AC%AC%E4%B8%89%E6%AC%A1%E6%BB%A1.png" width="600" height="320"/>

一直循环

4. 当Survivor的区域年龄达到了老年线的时候，此对象就会到达老年区

<img src="https://github.com/nullWolf007/images/raw/master/Java/jvm/GC/%E5%BD%93Survivor%E5%8C%BA%E7%9A%84%E5%AF%B9%E8%B1%A1%E5%B9%B4%E9%BE%84%E8%BE%BE%E5%88%B0%E8%80%81%E5%B9%B4%E7%BA%BF%E6%97%B6.png" width="600" height="320"/>

如此步骤，大体流程是

<img src="https://github.com/nullWolf007/images/raw/master/Java/jvm/GC/%E6%80%BB%E4%BD%93%E6%B5%81%E7%A8%8B.png" width="720" height="320"/>