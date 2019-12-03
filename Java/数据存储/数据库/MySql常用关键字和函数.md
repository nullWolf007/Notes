[TOC]

# MySql常用关键字和函数

### 参考

* [[【数据库】MySql常用函数梳理](https://www.cnblogs.com/ygj0930/p/5866388.html)](https://www.cnblogs.com/ygj0930/p/5866388.html)
* [MySQL 关键字执行顺序](https://blog.csdn.net/ai_shuyingzhixia/article/details/80559155)
* [MySQL自定义变量执行顺序](http://blog.itpub.net/29254281/viewspace-1415038/)

## 一、常用函数

### 1.1 数学函数

#### 1.1.1  RAND()

* 随机数生成，RAND()返回的数是完全随机的。对于RAND(X)生成的随机数是固定的，这次RAND(X)和下次RAND(X)是一致的

#### 1.1.2 ROUND(X)，ROUND(X,Y)

* ROUND(X)得到X的四舍五入的整数
* ROUND(X,Y)得到X的Y位四舍五入小数

#### 1.1.3 LOG(X,Y)

* LOG(X,Y)得到以X为底，Y的对数

#### 1.1.4 SORT(X)

* SORT(X)得到X的平方根

#### 1.1.5 MOD(X,Y)

* MOD(X,Y)：X对Y求余

#### 1.1.6 CEIL(X),CEILING(X)

* CEIL(X),CEILING(X)：向上取整

#### 1.1.7 FLOOR(X)

* FLOOR(X)：向下取整

#### 1.1.8 SIGN(X)

* SIGN(X)：返回X的符号，-1为负数，0不变，1为正数

#### 1.1.9 POW(X,Y)，POWER(X,Y)

* POW(X,Y)，POWER(X,Y)：幂运算，求X的Y次方幂

### 1.2 聚集函数

* 聚集函数一般配合GROUP BY语句使用

#### 1.2.1 AVG()

* AVG()：返回某列的平均值

#### 1.2.2 COUNT()

* COUNT()：返回某列/某组/某表的行数(即记录数)

#### 1.2.3 MAX()

* MAX()：返回某列的最大值

#### 1.2.4 MIN()

* MIN()：返回某列的最小值

#### 1.2.5 SUM()

* SUM()：返回某个列之和

### 1.3 字符串函数

#### 1.3.1 CONCAT()

* CONCAT(str1,str2,...)：返回结果为连接起来的字符串。如果任何一个参数为NULL，则返回NULL

#### 1.3.2 ASCII()

* ASCII(str)：返回字符串str的最左字符的数值。如果str为空，返回0；如果str为NULL，则返回NULL。将第一个字符转换ASCII编码的形式，如ASCII("a")为97

#### 1.3.3 BIN()

* BIN(N)：将十进制N转换成二进制的字符串

#### 1.3.4 CHAR_LENGTH()

* CHAR_LENGTH(str)：返回字符串的长度

#### 1.3.5 CONV()

* CONV(str,from_base,to_base)：将字符串从from_base进制转换成to_base进制

#### 1.3.6 ELT()

## 二、常用关键字

#### 常用关键字的顺序

```mysql
1.FROM：对FROM子句中前两个表执行笛卡尔积生成虚拟表vt1
2.ON：对vt1表应用ON筛选器只有满足 join_condition 为真的行才被插入vt2
3.OUTER(JOIN)：如果指定了 OUTER JOIN保留表(preserved table)中未找到的行将行作为外部行添加到vt2，生成t3，如果 from 包含两个以上表，则对上一个联结生成的结果表和下一个表重复执行步骤。
4.WHERE：对vt3应用 WHERE 筛选器只有使 where_condition 为true的行才被插入vt4
5.GROUP BY：按GROUP BY子句中的列列表对vt4中的行分组生成vt5
6.CUBE|ROLLUP：把超组(supergroups)插入vt6，生成vt6
7.HAVING：对vt6应用HAVING筛选器只有使 having_condition 为true的组才插入vt7
8.SELECT：处理select列表产生vt8
9.DISTINCT：将重复的行从vt8中去除产生vt9
10.ORDER BY：将vt9的行按order by子句中的列列表排序生成一个游标vc10
11.TOP
```

#### 自定义变量和关键字的顺序

* [MySQL自定义变量执行顺序](http://blog.itpub.net/29254281/viewspace-1415038/)

### 2.1 UNION和UNION ALL

#### 2.1.1UNION

* UNION用于连接两个以上的SELECT语句的结果组合到一个结果集合中。**多个SELECT语句会删除重复的数据**

#### 2.1.2UNION ALL

* UNION用于连接两个以上的SELECT语句的结果组合到一个结果集合中。**包括重复的数据**

#### 2.1.3语法格式

```mysql
SELECT expression1,expression2...
FROM table1
UNION[ALL]
SELECT expression1,expresiion2...
FROM table2
```

### 2.2 CASE WHEN

#### 2.2.1简单函数

```mysql
CASE [col_name] WHEN [value1] THEN [result1]...ELSE [default] END
```

* 实例

```mysql
  SELECT id,name,
  (CASE sex
  	WHEN '1' THEN '男'
      WHEN '2' THEN '女'
      ELSE '其他'
  END)
  FROM student;
```

#### 2.2.2搜索函数

```mysql
CASE WHEN [expr] THEN [result]...ELSE [default] END
```

* 实例

```mysql
SELECT id,name,
(CASE 
	WHEN age<18 THEN '少年'
    WHEN age<30 THEN '青年'
    WHEN age<50 THEN '中年'
    ELSE '老年'
END)
FROM user_info;
```

### 2.3 IF相关

#### 2.3.1 IF

* 语法规则（IF可以多层嵌套，如IF(id=1,1,IF(id=2,2,3))）

```mysql
IF(条件语句,true的时候执行的,false的时候执行的)
```

#### 2.3.2 IFNULL

* 语法规则：如果语句1不为null，返回语句1，否则返回语句2

```mysql
IFNULL(语句1，语句2)
```

#### 2.3.3 IF ELES

* 语法规则

```mysql
IF 条件1 THEN 
    语句1
[ELSEIF 条件2 THEN]  
    语句2
[ELSE 
    语句3]  
END IF 
```

### 2.4 JOIN相关

![JOIN相关](https://github.com/nullWolf007/images/raw/master/%E6%95%B0%E6%8D%AE%E5%BA%93/%E5%B8%B8%E7%94%A8%E5%85%B3%E9%94%AE%E5%AD%97/join%E7%9B%B8%E5%85%B3.png)

### 2.5 GROUP BY

#### 2.5.1含义

* GROUP BY 语句根据一个或多个列对结果集进行分组。会把值相同放到一个组中，最终查询出的结果**只会显示组中一条记录。**

* 由于只显示组中一条数据，所以经常和sum，max，count之类的函数一起使用

  