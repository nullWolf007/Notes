# UNION 和 UNION ALL

## 一、UNION

* UNION用于连接两个以上的SELECT语句的结果组合到一个结果集合中。多个SELECT语句会删除重复的数据

### 二、UNION ALL

* UNION用于连接两个以上的SELECT语句的结果组合到一个结果集合中。包括重复的数据

## 三、语法格式

```mysql
SELECT expression1,expression2...
FROM table1
UNION[ALL]
SELECT expression1,expresiion2...
FROM table2
```


