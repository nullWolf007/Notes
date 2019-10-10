[TOC]

# Volatile

### 参考

* [面试必问的 volatile，你了解多少？](https://www.jianshu.com/p/506c1e38a922)
* [**java volatile关键字解惑**](https://www.jianshu.com/p/195ae7c77afe)

## 一、重排序

### 1.1 重排序

* 代码

```java
public class VolatileTest {
    int a = 0;
    int b = 0;

    public void set() {
        a = 1;
        b = 1;
    }

    public void loop() {
        while (b == 0) continue;
        if (a == 1) {
            System.out.println("i'm here");
        } else {
            System.out.println("what's wrong");
        }
    }
}
```

* 解释

```text
VolatileTest类有两个方法，分别是set()和loop(),假设线程B执行loop()，线程A执行set()，会得到结果？
答案不确定，涉及编译器的重排序和CPU指令的重排序
```

### 1.2 编译器重排序

* 