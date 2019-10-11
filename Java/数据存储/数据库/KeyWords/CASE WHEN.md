[TOC]

# CASE WHEN

## 一、简单函数

```mysql
CASE [col_name] WHEN [value1] THEN [result1]...ELSE [default] END
```

* 实例

  ```mysql
  SELECT id,name,(CASE sex
                 WHEN '1' THEN '男'
                 WHEN '2' THEN '女'
                 ELSE '其他'
                 END)
  FROM student;
  ```

## 二、搜索函数

```mysql
CASE WHEN [expr] THEN [result]...ELSE [default] END
```

* 实例

  ```mysql
  SELECT id,name,(CASE 
                 WHEN age<18 THEN '少年'
                 WHEN age<30 THEN '青年'
                 WHEN age<50 THEN '中年'
                 ELSE '老年'
                 END)
  FROM user_info;
  ```

  