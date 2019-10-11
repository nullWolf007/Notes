# CSS

## 块元素和内联元素

### 内联元素和块级元素的区别
**块级元素的特点**
* 总是从新行开始
* 高度，行高，外边距以及内边距都可以控制
* 宽度默认是容器的100%
* 可以容纳内联元素和其他块元素

**内联元素的特点**
* 和相邻行内元素在同一行
* 高，宽无效，但是水平方向的padding和margin可以设置，垂直方向的无效
* 宽度就是他本身内容的宽度
* 内敛元素只能容纳文本或者其他内联元素

### 块级元素和内联元素之间的转换
**display**
* 块元素默认display:block;行内非替换元素(a,span)默认为display:inline;行内替换元素(input)默认为display:inline-block
  * display:none 不显示该元素，也不会保留该元素原先占有的文档流位置
  * display:block 转换为块级元素
  * display:inline 转换为行内元素
  * display:inline-block 转换为行内块级元素
  
**float**
* 把行内元素设置完float:left/right后，该元素的display属性会被赋予block值，且拥有浮动特性，行内元素去除之间莫名的空白

**position**
* 当为行内元素进行定位的时候，position:absolute与position:fixed都会使原先的行内元素变成块级元素
