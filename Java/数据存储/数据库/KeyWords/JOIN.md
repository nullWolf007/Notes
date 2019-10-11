[TOC]

# JOIN

## 一、表数据

### 1.1 userinfo

```mysql
+----+----------+----------+-------+
| id | username | password | phone |
+----+----------+----------+-------+

```

### 1.2 follow

```mysql
+----+----------+------------+
| id | selfname | followname |
+----+----------+------------+
```

## 二、笛卡尔积：CROSS JOIN

* 笛卡尔积就是将A表的每一条记录和B表的每一条记录强行拼一起。所以A表条记录，B表有m条记录，笛卡尔积会产生n*m条记录

* 实例

  ```mysql
  SELECT * FROM userinfo CROSS JOIN follow;
  #其他四种实现方式
  SELECT * FROM userinfo INNER JOIN follow;
  SELECT * FROM userinfo,follow;
  SELECT * FROM userinfo NATURE JOIN follow;
  select * from userinfo NATURA join follow;
  ```

## 三、内连接：INNER JOIN

* 按条件求两个表的交集

* 实例

  ```mysql
   SELECT * FROM userinfo INNER JOIN follow ON userinfo.username=follow.selfname;
  ```


## 四、左连接：LEFT JOIN

* 左连接LEFT JOIN求两个表的交集外加左表剩下的数据

* 实例

  ```mysql
   SELECT * FROM userinfo LEFT JOIN follow ON userinfo.username=follow.selfname;
  ```

## 五、右连接：RIGHT JOIN

* 右连接RIGHT JOIN求两个表的交集外加右表剩下的数据
* 实例

  ```mysql
   SELECT * FROM userinfo RIGHT JOIN follow ON userinfo.username=follow.selfname;
  ```


## 六 、USING子句

* ON子句语法为：table1.column_name1=table2.column_name2。当模式设计对联表的列采取相同的命名时，即column_name1==column_name2。就可以使用USING语法来简化ON语法。USING(column_name)



https://www.cnblogs.com/fudashi/p/7491039.html