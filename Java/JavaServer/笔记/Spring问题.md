# Spring问题

## 1.整体概要

```text
controller负责接收数据，对数据进行分类，发送给相应的Interface Service。
然后会去找到对应的实现类serviceImpl，serviceImpl会把数据进行组装然后给dao层。
serviceImpl有对应dao层的mapper，然后mapper去找xml中的sql语句进行增删改查
mapper去找xml采用的是MyBatis
```

## 2.为什么controller里使用的@Autowired跟着的是接口service?

```Text
Spring中的@Autowired自动装配的大体机制是：
首先在spring配置context:component-scan，扫描指定包下的类。
如果扫描到有@Component@Controller@Service等这些注解的类，则把这些类注册为bean，
当Spring容器启动时，AutowiredAnnotationBeanPostProcessor将扫描Spring容器中所有Bean，
当发现Bean中拥有@Autowired注释时就找到和其匹配（默认按类型匹配）的Bean，并注入到对应的地方中去，
也就是为@Autowired修饰的接口赋值，具体使用的时反射机制
```

### @Autowired的四种模式(默认byType)

* byName：根据属性名自动装配。此选项将检查容器并根据名字查找与属性完全一致的bean，并将其与属性自动装配。
* byType：如果容器中存在一个与指定类型相同的bean，那么将与该属性自动装配。如果存在多个该类型的bean，那么将抛出异常，并指出不能使用byType方式进行自动装配。若没有找到相匹配的bean，则什么事都不发生，属性也不会被设置。
* constructor：与byType的方式类似，不同之处在于它应用于构造器参数。如果在容器中没有找到于构造器类型一致的bean，那么将会抛出异常\
* autodetect：通过bean类的自省机制来决定时使用constructor还是byType方式进行自动装配。如果发现默认的构造器，那么将使用byType方式

### @Autowired如果存在两个实现类会怎么样？

```text
只使用@Autowired如果存在两个实现类会报错，可以使用@Qualifier来进行注解指明使用哪一个实现类，
实际上也是通过byName方式实现。
```

### 详细说明：参考文章 [Spring MVC 解读——@Autowired、@Controller、@Service从原理层面来分析](https://blog.csdn.net/mack415858775/article/details/47721909)
