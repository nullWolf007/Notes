# JDBC

## 前言
* 概念
> JDBC(Java Data Base Connectivity)：java数据库连接，是一种用于执行SQL语句的Java API

## JDBC开发步骤
1. 注册驱动
> 告知JVM使用的是哪一个数据库的驱动
2. 获得连接
> 使用JDBC中的类，完成对MySQL数据库的连接
3. 获得语句执行平台
> 通过连接对象获取对SQL语句的执行者对象
4. 执行sql语句
> 使用执行者对象，向数据库执行SQL语句，获取到数据库的执行后的结果
5. 处理结果
> 
6. 释放资源
> 一堆close()
* 代码样例
```java
public static void main(String[] args) throws ClassNotFoundException, SQLException {
	// 1.注册驱动 反射技术，将驱动类加入到内容
	// registerDriver(Driver driver)
	// Driver是一个接口 使用jar包中的实现类
	// DriverManager.registerDriver(new Driver());
	// 驱动类源代码，注册两次驱动程序
	Class.forName("com.mysql.jdbc.Driver");

	// 2.获得数据库连接
	// DriverManager getConnection（url,user,password）
	// 返回值是Connection接口的实现类
	// url 数据库地址 jdbc:mysql://连接主机IP:端口号//数据库名字
	String url = "jdbc:mysql://localhost:3306/mybase";
	String username = "root";
	String password = "";
	Connection con = DriverManager.getConnection(url, username, password);
		
	//3.获得语句执行平台，通过数据库连接对象，获得到执行SQL语句的执行对象
	//con对象调用方法 Statement createStatement()将SQL语句发送到数据库
	//返回值Statement接口的实现类对象
	Statement stat = con.createStatement();
		
	//4.执行sql语句，获取结果
	//int executeUpdate()  insert delete update
	//返回值 int 操作成功数据表多少行
	int row = stat.executeUpdate("INSERT INTO sort(sname,sprice,sdesc) VALUES('汽车',50000,'涨价')");
	System.out.println(row);
		
		
	//6.释放资源
	stat.close();
	con.close();
}
```

## 防止注入攻击
* 使用preparedStatement:Statement的子接口
> SQL预编译存储，多次高效的执行SQL

 
