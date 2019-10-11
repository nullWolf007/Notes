# MySQL数据库

## SQL语句
### DDL(Data Definition Language)定义
* 创建数据库 
> create database 数据库;
* 显示数据库 
> show databases;
* 删除数据库
> drop database 数据库;
* 切换/使用数据库
> use 数据库;
* 创建数据表
```mysql
create table 表(
    列名1 数据类型 约束,
    列名2 数据类型 约束,
    ...
);
```
* 查看数据表
> show tables;
* 查看表的格式
> desc 表;
* 删除表
> drop table 表;
### DML(Data Manipulation Language)操纵
* 修改表添加列
> alter table 表名 add 列名 数据类型  约束;
* 修改表修改列(可以修改列名，数据类型约束)
> alter table 表名 modify 列名 数据类型 约束;
* 修改列名
> alter table 表名 change 旧列名 新列名 数据类型 约束;
* 删除列
> alter table 表名 drop 列名;
* 修改表名
> rename table 旧表名 to 新表名;
* 添加数据
> insert into 表名(列名1，列名2，列名3) values (值1，值2，值3);
* 添加数据，不考虑主键
> insert into 表名 (列名) values (值);
* 添加数据，所有值全给出
> insert into 表名 values (全列值);
* 添加数据，批量写入
> insert into 表名 (列名) values (值1),(值2);
* 对数据进行更新操作
> update 表名 set 列1=值1,列2=值2 where 条件;
* 删除表中数据
> delete from 表名 where 条件
* 删除表中所有数据
> truncate table 表名;
### DCL(Data Control Language)控制
### DQL(Data Query Language)查询
* 查询指定列的数据
> select 列名1,列名2 from 表名;
* 查询所有的数据
> select * from 表名;
* 查询去掉重复记录distinct
> select distinct 列名 from 表名;
* 查询**临时**重新命名列
> select 列名 as 新列名 from 表名;
* 查询数据中，进行数学计算
> select 列名数学计算 from 表名
* 模糊查询like,配合通配符%
> like '%支出%'
* 查询字符个数
> 个数个_  例如2个字符 __
* 不为空的
> is not null
* 升序，降序
> order by 列名 asc

> order by 列名 desc
* 求和count，对表中数据的个数求和
> count(列名)
* sum求和，对一列中数据进行求和计算 
> sum(列名)
* max(列名)对某列获取最大值
* avg(列名)获取某列的平均值，空值忽略
* 分组查询，必须跟随聚合函数，select查询的时候，被分组的列，要出现在select选择列的后面
> group by 被分组的列名

## 知识点
* 主键约束
> 主键约束，主键约束的字段是非空，唯一的，在开发中一般来说主键是不具备任何含义的，用来唯一标志的. primary key
* 自动增长
> auto_increment
* 数据库的不等于
```java
<>
```
* 数据库的与或非
```text
and or not 
```
* 包含
> in   比如 id in (1,3,5,7)
* delete from 表名 和 truncate table 表名区别
```text
delete 一条条删除，不清空auto_increment记录数
truncate 直接将表删除，重新建表，auto_increment将置为零，重新开始
```
* 两者之间
> between and
* 解决数据库cmd乱码
> set names 'gbk'
* 先过滤条件，再进行排序；先条件后分组；先分组后排序；
* 分组后再次过滤 having

## 重点
* 开启事务
> start transaction
* 找回
> rollback
