# IO

# 1.前言
## IO操作
* 输入操作:把持久设备上的数据读取到内存中的动作，从文件到java程序
* 输出操作:把需要的内存中的数据存储到持久化设备上的动作，从java程序到文件
## 类型
* 字节流:OutputStream,InputStream
* 字符流:Writer,Reader
* 转换流：

# 2.File类
## 描述
* 对文件夹，文件，路径进行操作的类

# 3.IO流对象
# a.字节流
## 字节输出流OutputStream
* 概念：抽象类，从java程序中写出文件，操作的单位是字节，可以写任意文件，都是写入方法write
* 常用方法
  * write(int b): 写入1个字节
  * write(byte[] b)：写入字节数组
  * write(byte[] b,int,int)：写入字节数组，int开始写入的索引，int写几个
  * close():关闭流对象，释放与流相关的资源
## OutputStream的子类
### FileOutputStream
* 流对象的构造方法FileOutputStream(arg)可以创建文件，如果文件存在，直接覆盖
* 文件的续写FileOutputStream(arg,append),如果构造方法的append为true，就可以续写了
## 字节输入流InputStream
* 概念:抽象类，读取任意文件，操作的单位是字节
* 常用方法
  * read()
## InputStream的子类
### FileInputStream
# b.字符流
## 字符输出流Write
* 写入字符流的抽象类，所有字符输出流的超类
## Writer的子类
### FileWriter
* 字符输出流写数据的时候，必须运行刷新flush方法，才能完成写入
## 字符输入流Read
* 读取文本文件,所有字符输入流的超类
## Reader的子类
### FileReader
# c.转换流
### OutputStreamWriter
* 继承Writer类，是一个字符输出流，写文本文件
* 可以将字符流转换成字节流
* 作用:可以指定编码方式
### InputStreamReader
* 字节流转换成字符流
* 可以指定编码方式
# d.缓冲流
* 作用：提高效率
## 字节缓冲输出流BufferedOutputStream
* 继承OutputStream
## 字节缓冲输入流BufferedInputStream
* 继承InputStream
## 字符缓冲输出流BufferedWriter
## 字符缓冲输入流BufferedReader
# e.Properties类
* 继承Hashtable，实现Map接口，所以是键值对形式
* 没有泛型，键值对都是字符串
* 可以持久化存储，可以存储到持久化设备中
## 常用方法
* setProperty:等同于Hashtable的put方法
* getProperty:等同于Hasttable的get方法
* stringPropertyNames:将集合中的键存储到Set集合中，类似于Map接口的方法keySet
* load:输入流对象读取文件中的键值对，保存到Properties集合中 
* store:参数是输出流，将集合通过输出流对象保存到文件中
# f.序列化流和反序列化流
## 序列化流ObjectOutputStream
* 将对象以流的形式写到文件中，实现序列化，对象必须实现Serializable(标记型接口)接口
* writeObject方法
## 反序列化流ObjectInputStream
* 将文件对象以流的形式读取出来
* readObject方法
## 静态不属于对象，静态数据不能序列化
## transient关键字
* 阻止成员变量序列化
## 注意点 
* 序列化反序列化的时候，对象类不能改变，对象的class文件必须存在，没有改变，否则会抛出异常(class文件生成时候会产生特定的序列号，改变了，序列号不一样会抛出异常)
* 解决办法：即使修改了源代码，也不重新计算序列号。**自定义序列号**
```java
//类自定义序列号，编译器不会自动计算序列号
static final long serialVersionUID = 1L;
```
# g.打印流
## PrintStream  PrintWriter
* 永远不会抛出IOException，但是可能抛出别的异常
* 两个打印流的构造方法不一样，打印流的输出目的端PrintWriter多一个字符输出流对象，其他方法是一致的
# h.commons-IO工具jar包
## FilenameUtils处理文件名
### 静态常用方法
* getExtension：返回文件的扩展名
* getName:获取文件名
* isExtension：判断文件名是不是以后缀名结尾
## FileUtils文件操作
### 静态常用方法
* readFileToString:读取文件内容，并返回String
* writeStringToFile：把字符串写到文件中
* copyFile:复制源文件到指定文件
* copyDirectoryToDirectory：复制文件夹到指定文件夹


#  4.应用
* IO实现互联网数据的传输
* IO流对象实现文件的复制
