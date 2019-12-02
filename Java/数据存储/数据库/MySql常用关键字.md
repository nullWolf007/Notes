[TOC]

# MySql常用关键字

## 一、UNION和UNION ALL

### 1.1UNION

* UNION用于连接两个以上的SELECT语句的结果组合到一个结果集合中。**多个SELECT语句会删除重复的数据**

### 1.2UNION ALL

* UNION用于连接两个以上的SELECT语句的结果组合到一个结果集合中。**包括重复的数据**

### 1.3语法格式

```mysql
SELECT expression1,expression2...
FROM table1
UNION[ALL]
SELECT expression1,expresiion2...
FROM table2
```

## 二、CASE WHEN

### 2.1简单函数

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

### 2.2搜索函数

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

## 三、IF相关

* 语法规则（IF可以多层嵌套，如IF(id=1,1,IF(id=2,2,3))）

```mysql
IF(条件语句,true的时候执行的,false的时候执行的)
```

## 四、JOIN相关

![JOIN相关](https://github.com/nullWolf007/images/raw/master/%E6%95%B0%E6%8D%AE%E5%BA%93/%E5%B8%B8%E7%94%A8%E5%85%B3%E9%94%AE%E5%AD%97/join%E7%9B%B8%E5%85%B3.png)
