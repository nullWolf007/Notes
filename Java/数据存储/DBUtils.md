# DBUtils和连接池

## 前言
* 执行成功 提交事务 commit
* 执行失败 回滚事务 rollback
* JavaBean必须有空参构造器

## 常用类
### ResultSetHandle结果处理接口的实现类
* ArrayHandle:把数据库查询的数据第一行存储到数组对象中
* ArrayListHandle:将数据库查询的每一行存储到数组中，再放到List集合中
* BeanHandle：把数据库查询的第一行数据放到JavaBean中
* BeanListHandle:把数据库查询的每一行数据放到Javaean中，再放到List集合中
* ColumnListHandle:把数据库查询的数据的指定列数据放到List集合中
* ScalarHandler:用于单数据，例如select count(```*```) from 表
* MapHandler:把数据库查询的数据的第一行存储到Map集合中  键:列名 值:这列的数据。Map是有序的Map
* MapListHandler:把数据库查询的数据的每一行存储到Map集合中，再把Map放到List中,Map是有序的Map

### QueryRunner常用方法
* update(Connection con,String sql,Object...params);返回int值
* query(Connection con,String sql,ResultSetHandle```<T>``` rsh,Object...params)

## 连接池
### DataSource接口
### DBCP连接池
* 使用jar包  commons-dbcp-1.4.jar
* BasicDataSource类
> getConnection()
* 代码实例
```java
public static void main(String[] args) {
	// 创建DataSource接口的实现类
	BasicDataSource dataSource = new BasicDataSource();
	// 连接数据库的4个信息，set方法
	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	dataSource.setUrl("jdbc:mysql://localhost:3306/mybase");
	dataSource.setUsername("root");
	dataSource.setPassword("");

	//设置对象连接池的连接数量
	dataSource.setInitialSize(10);//初始化连接数
	dataSource.setMaxActive(8);//最大连接数量
	dataSource.setMaxIdle(5);//最大空闲连接
	dataSource.setMinIdle(1);//最小空闲连接
		
	// getConnection获取数据库连接
	try {
		Connection con = dataSource.getConnection();
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		throw new RuntimeException("数据库连接失败");
	}
}
```

### Tomcat 
* JavaWeb服务器，把写好的class文件，放在Tomcat软件中，
