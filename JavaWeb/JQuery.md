# JQuery

## 基本使用
**1.**
```java
$(function(){
  Jquery代码
});
```
**2.获取元素**
```java
$("#id");
```
**3.选择器**
```java
//id选择器
$("#id名称");

//元素选择器
$("元素名称");

//类选择器
$(".类名");

//通配符 *
```
**4.层级选择器**
```java
//ancestor descendant:给定祖先元素下匹配所有的后代元素
$("body div")

//parent > child 给定父元素下匹配的所有子元素
$("body>div")

//prev+ next 匹配所有紧接在prev元素后的next元素(同桌)

//prev ~ siblings 匹配prev元素之后所有sibling元素(兄弟)

```
**5.基本过滤选择器**
```java
//获取第一个元素first
$('li').first()  等价于 $("li:first")

//:last
//:not
//:even
//:odd
//:eq
//:gt
//:lt
//:header
//:animated
```
**6.属性选择器**
```java
//[attribute]匹配包含给定属性的元素
$("div[id]")

//[attribute=value]匹配给定的属性是某个特定值的元素
$("div[id="demo1"]")

//[attribute!=value]
//[attribute^=value]匹配给定的属性是以某些值开始的元素
//[attribute$=value]
//[attribute*=value]
//[selector1][selector2][selectorN]

```
**7.表单选择器**
```java
//:input
//:text
//:password
//:radio
//:checkbox
//:submit
//:image
//:reset
//:button
//:file
//:hidden
//:enabled
//:disabled
//:checked
//:selected
```

## 常用函数
### 核心
* 遍历函数
```jquery
//通用遍历方法，可用于遍历对象和数组，object表示需要遍历的对象或数组，callback回调函数有两个参数，第一个为对象的成员或者数组的索引，第二个为对应变量或内容
jQuery.each(object,[callback])

//为每一个匹配的元素作为上下文来执行一个函数
each(callback)
```
### 文档处理
* append():A.append(B) 将B追加到A内容的末尾处
* appendTo:A.appendTo(B) 将A加到B内容的末尾处
### 属性操作
* val() 获取value属性的值
* val(...) 给value属性设置值
* html() 获得html代码，如果有标签，一并获得
* html(...) 设置html代码，如果有标签，将进行解析
* text() 获得文本，如果有标签，忽略
* text(...) 设置文本，如果含有标签，不进行解析，原样输出

## 事件处理
* 事件bind绑定
* 事件解除绑定unbind


## 格式要求
* jquery对象使用$开头，dom对象不用$
```jquery
var $optEle
var optEle
```
* 使用this对象，如果是jquery对象，则要使用$包起来
