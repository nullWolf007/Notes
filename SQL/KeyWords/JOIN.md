# JOIN

## 表数据

### 一、userinfo

```mysql
+----+----------+----------+-------+
| id | username | password | phone |
+----+----------+----------+-------+
|  1 | mumu     | 123      | 12213 |
|  2 | nan      | 234      | 13455 |
+----+----------+----------+-------+
```

### 二、follow

```mysql
+----+----------+------------+
| id | selfname | followname |
+----+----------+------------+
|  1 | nullWolf | mumu       |
|  2 | nullWolf | whoami     |
|  3 | mumu     | nullWolf   |
|  4 | mumu     | whoami     |
+----+----------+------------+
```

## 几种JOIN

### 一、笛卡尔积：CROSS JOIN

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

  ```mysql
  +----+----------+----------+-------+----+----------+------------+
  | id | username | password | phone | id | selfname | followname |
  +----+----------+----------+-------+----+----------+------------+
  |  1 | mumu     | 123      | 12213 |  1 | nullWolf | mumu       |
  |  2 | nan      | 234      | 13455 |  1 | nullWolf | mumu       |
  |  1 | mumu     | 123      | 12213 |  2 | nullWolf | whoami     |
  |  2 | nan      | 234      | 13455 |  2 | nullWolf | whoami     |
  |  1 | mumu     | 123      | 12213 |  3 | mumu     | nullWolf   |
  |  2 | nan      | 234      | 13455 |  3 | mumu     | nullWolf   |
  |  1 | mumu     | 123      | 12213 |  4 | mumu     | whoami     |
  |  2 | nan      | 234      | 13455 |  4 | mumu     | whoami     |
  +----+----------+----------+-------+----+----------+------------+
  ```

### 二、内连接：INNER JOIN

* 按条件求两个表的交集

* 实例

  ```mysql
   SELECT * FROM userinfo INNER JOIN follow ON userinfo.username=follow.selfname;
  ```

  ```mysql
  +----+----------+----------+-------+----+----------+------------+
  | id | username | password | phone | id | selfname | followname |
  +----+----------+----------+-------+----+----------+------------+
  |  1 | mumu     | 123      | 12213 |  3 | mumu     | nullWolf   |
  |  1 | mumu     | 123      | 12213 |  4 | mumu     | whoami     |
  +----+----------+----------+-------+----+----------+------------+
  ```

### 三、左连接：LEFT JOIN

* 左连接LEFT JOIN求两个表的交集外加左表剩下的数据





https://www.cnblogs.com/fudashi/p/7491039.html