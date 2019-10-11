[TOC]

# Spring

### 参考文章

* [细说Spring——AOP详解（AOP概览）](https://blog.csdn.net/q982151756/article/details/80513340)

## 一、概述

### 概念

```txt
以IoC(控制反转)和AOP（面向切面编程）两种技术为基础
```

### 七大模块

* 核心模块（Core）

```txt
1.是Spring的核心容器，提供了Spring框架的基本功能；
2.此模块包含的BeanFactory类是Spring的核心类，负责产生和管理Bean，是工程模式的实现；
3.采用Factory(工厂模式)实现了IOC（控制反转）将应用的配置和依赖性规范与实际的应用程序代码分开；
Spring以bean的方式组织和管理Java应用中发各个组件及其关系。
```

* Context模块

```txt
1.是一个配置文件，向Spring框架提供上下文信息；			
2.SpringContext模块继承BeanFactory类，添加了事件处理、国际化、资源装载、透明装载、以及数据校验等功能;
3.还提供了框架式的Bean的访问方式和企业级的功能，如JNDI访问，支持EJB、远程调用、继承模板框架、Email和定时任务调度等；
```

* AOP模块

```txt
1.Spring AOP直接将面向方面的编程功能集成到了Spring框架中，
所以很容易的使Spring框架管理的任何对象支持AOP（Spring集成了所有AOP功能。通过事务管理可以使任意Spring管理的对象AOP化）；
2.Spring AOP为基于Spring的应用程序中的对象提供了事务管理服务；
3.通过使用Spring AOP，不用依赖EJB组件，就可以将声明性事务管集成到应用程序中。
```

* DAO模块

```txt
1.DAO（DataAccessObject）模式思想是将业务逻辑代码与数据库交互代码分离，降低两者耦合；
2.通过DAO模式可以使结构变得更为清晰，代码更为简；
3.DAO模块中的JDBC的抽象层，提供了有意义的异常层次结构，用该结构来管理异常处理，和不同数据库供应商所抛出的错误信息；
4.异常层次结构简化了数据库厂商的异常错误（不再从SQLException继承大批代码），
极大的降低了需要编写的代码数量，并且提供了对声明式事务和编程式事务的支持；
```

* O/R映射模块

```txt
1.SpringORM模块提供了对现有ORM框架的支持；
2.提供了ORM对象的关系工具，其中包括了Hibernate、JDO和 IBatis SQL Map等，
所有的这些都遵从Spring的通用事务和DAO异常层次结构；
3.注意这里Spring是提供各类的接口（support），目前比较流行的下层数据库封闭映射框架，如ibatis,Hibernate等；
```

* Web模块

```txt
1.此模块建立在SpringContext基础之上，提供了Servlet监听器的Context和Web应用的上下文；
2.对现有的Web框架，如JSF、Tapestry、Structs等提供了集成；
3.SpringWeb模块还简化了处理多部分请求以及将请求参数绑定到域对象的工作。
```

* MVC模块

```txt
1.SpringWebMVC模块建立在Spring核心功能之上，拥有Spring框架的所有特性，
能够适应多种多视图、模板技术、国际化和验证服务，实现控制逻辑和业务逻辑的清晰分离；
2.通过策略接口，MVC 框架变成为高度可配置的，MVC 容纳了大量视图技术，其中包括 JSP、Velocity、Tiles、iText 和 POI;
3.MVC模型:
3.1.Servlet控制器为应用程序提供了一个进行前-后端处理的中枢。
一方面为输入数据的验证、身份认证、日志及实现国际化编程提供了一个合适的切入点；
另一方面也提供了将业务逻辑从JSP文件剥离的可能;
3.2.业务逻辑从JSP页面分离后，JSP文件蜕变成一个单纯完成显示任务的东西，这就是常说的View;
3.3.而独立出来的事务逻辑变成人们常说的Model，再加上控制器Control本身，就构成了MVC模式
```

### 使用BeanFactory管理bean

BeanFactory采用了Java经典的工厂模式，通过从XML配置文件或属性文件(.properties)中读取了JavaBean的定义，来实现JavaBean的创建、配置和管理。BeanFactory有很多实现类，其中XmlBeanFactory可以通过流行的XML文件格式读取配置信息来装载JavaBean。

## 二、控制反转IoC和依赖注入

### 概念

IoC（Inversion of Control），即控制反转。使程序组件或类之间尽量形成一种松耦合的结构。开发者在使用类的实例之前，需要先创建对象的实例。但是IoC将创建实例的任务交给了IoC容器，这样开发应用代码时只需要使用类的实例，这就是IoC。IoC最常见的方式叫做依赖注入（Dependency Injection，简称DI）

### 实例

* 其中id属性为bean的名称，class属性为对应的类名，这样通过BeanFactory容器的getBean("test")方法接可以获取到该类的实例

```xml
<bean id="test" class="com.mr.Test"/>
```

## 三、AOP（面向切面编程）

### 术语

* 切面（Aspect）

```txt
切面是对象操作过程中的截面
实际上“切面”是一段程序代码，这段代码将被“植入”到程序流程中
```

* 连接点（Join Point）

```xml
对象操作过程中的某个阶段点
实际上是对象的一个操作，如：对象调用某个方法、读写对象的实例或者某个方法抛出异常等
```

* 切入点（Pointcut）

```txt
切入点是连接点的集合，这些连接点通过逻辑关系组合起来或是通过通配、正则等方式集中起来
切面与程序流程的“交叉点”便是程序的切入点
```

* 通知（Advice）

```txt
通知是某个切入点被横切后，所采取的处理逻辑
也就是，在切入点处拦截程序后，通过通知来执行切面
```

* 目标对象（Target）

```txt
所有被通知的对象（也可以理解为被代理的对象）都是目标对象。
目标对象被AOP所关注，、它的属性的改变会被关注，它的行为的调用也会被关注，它的方法传参的变化仍然会被关注，
AOP会注意目标对象的变动，随时准备向目标对象“注入切面”
```

* 织入（Weaving）

```txt
织入是将切面功能应用到目标对象的过程。由代理工厂创建一个代理对象，这个代理可以为目标对象执行切面功能
```

* 引入（Introduction）

```txt
对一个已编译完类（class），在运行时期，动态地向这个类加载属性和方法
```

**然后举一个容易理解的例子**： 
看完了上面的理论部分知识, 我相信还是会有不少朋友感觉到 AOP 的概念还是很模糊, 对 AOP 中的各种概念理解的还不是很透彻. 其实这很正常, 因为 AOP 中的概念是在是太多了, 我当时也是花了老大劲才梳理清楚的. 
下面我以一个简单的例子来比喻一下 AOP 中 `Aspect`, `Joint point`, `Pointcut` 与 `Advice`之间的关系. 
让我们来假设一下, 从前有一个叫爪哇的小县城, 在一个月黑风高的晚上, 这个县城中发生了命案. 作案的凶手十分狡猾, 现场没有留下什么有价值的线索. 不过万幸的是, 刚从隔壁回来的老王恰好在这时候无意中发现了凶手行凶的过程, 但是由于天色已晚, 加上凶手蒙着面, 老王并没有看清凶手的面目, 只知道凶手是个男性, 身高约七尺五寸. 爪哇县的县令根据老王的描述, 对守门的士兵下命令说: 凡是发现有身高七尺五寸的男性, 都要抓过来审问. 士兵当然不敢违背县令的命令, 只好把进出城的所有符合条件的人都抓了起来.

来让我们看一下上面的一个小故事和 AOP 到底有什么对应关系. 
首先我们知道, 在 Spring AOP 中 `Joint point` 指代的是所有方法的执行点, 而 point cut 是一个描述信息, 它修饰的是 `Joint point`, 通过 point cut, 我们就可以确定哪些 `Joint point` 可以被织入 `Advice`. 对应到我们在上面举的例子, 我们可以做一个简单的类比, **Joint point 就相当于 爪哇的小县城里的百姓**,**pointcut 就相当于 老王所做的指控, 即凶手是个男性, 身高约七尺五寸**, **而 Advice 则是施加在符合老王所描述的嫌疑人的动作: 抓过来审问**. 
为什么可以这样类比呢?

- `Joint point` ： 爪哇的小县城里的百姓: 因为根据定义, `Joint point` 是所有可能被织入 `Advice` 的候选的点, 在 Spring AOP中, 则可以认为所有方法执行点都是 `Joint point`. 而在我们上面的例子中, 命案发生在小县城中, 按理说在此县城中的所有人都有可能是嫌疑人.
- `Pointcut` ：男性, 身高约七尺五寸: 我们知道, 所有的方法(joint point) 都可以织入 `Advice`, 但是我们并不希望在所有方法上都织入 `Advice`, 而 `Pointcut` 的作用就是提供一组规则来匹配joinpoint, 给满足规则的 joinpoint 添加 `Advice`. 同理, 对于县令来说, 他再昏庸, 也知道不能把县城中的所有百姓都抓起来审问, 而是根据凶手是个男性, 身高约七尺五寸, 把符合条件的人抓起来. 在这里 凶手是个男性, 身高约七尺五寸 就是一个修饰谓语, 它限定了凶手的范围, 满足此修饰规则的百姓都是嫌疑人, 都需要抓起来审问.
- `Advice` ：抓过来审问, `Advice` 是一个动作, 即一段 Java 代码, 这段 Java 代码是作用于 point cut 所限定的那些 `Joint point` 上的. 同理, 对比到我们的例子中, 抓过来审问 这个动作就是对作用于那些满足 男性, 身高约七尺五寸 的爪哇的小县城里的百姓.
- `Aspect`:：`Aspect` 是 point cut 与 `Advice` 的组合, 因此在这里我们就可以类比: “根据老王的线索, 凡是发现有身高七尺五寸的男性, 都要抓过来审问” 这一整个动作可以被认为是一个 `Aspect`.

**最后是一个描述这些概念之间关系的图**： 
![这里写图片描述](https://img-blog.csdn.net/20180530175605692?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3E5ODIxNTE3NTY=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

## 四、其他的一些内容

`AOP`中的`Joinpoint`可以有多种类型：构造方法调用，字段的设置和获取，方法的调用，方法的执行，异常的处理执行，类的初始化。也就是说在`AOP`的概念中我们可以在上面的这些`Joinpoint`上织入我们自定义的`Advice`，但是在`Spring`中却没有实现上面所有的`joinpoint`，确切的说，`Spring`只支持方法执行类型的`Joinpoint`。

**Advice 的类型**

- `before advice`, 在 join point 前被执行的 advice. 虽然 before advice 是在 join point 前被执行, 但是它并不能够阻止 join point 的执行, 除非发生了异常(即我们在 before advice 代码中, 不能人为地决定是否继续执行 join point 中的代码)
- `after return advice`, 在一个 join point 正常返回后执行的 advice
- `after throwing advice`, 当一个 join point 抛出异常后执行的 advice
- `after(final) advice`, 无论一个 join point 是正常退出还是发生了异常, 都会被执行的 advice.
- `around advice`, 在 join point 前和 joint point 退出后都执行的 advice. 这个是最常用的 advice.
- `introduction`，introduction可以为原有的对象增加新的属性和方法。

在`Spring`中，通过动态代理和动态字节码技术实现了`AOP`，这些内容，我们将在以后进行讲解。