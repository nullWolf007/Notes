# SpringBoot+MyBatis项目

## pom.xml

### 常用依赖

```xml
<dependencies>
        <!-- Spring Boot Web 依赖 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion><!-- 去掉默认配置logback -->
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency> <!-- 引入log4j2依赖 -->
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j2</artifactId>
        </dependency>

        <!-- Springboot aop切面 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>

        <!-- 缓存 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>

        <!-- Spring Boot Mybatis 依赖 -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.3.1</version>
        </dependency>
        <!-- mybatis包 -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.2</version>
        </dependency>
        <!-- MySQL 连接驱动依赖 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <!-- 连接池依赖包 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.0</version>
        </dependency>
        <!-- Spring Boot Test 依赖 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!--添加适用于生产环境的功能，如性能指标和监测等功能。 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
    </dependencies>
```

## application.properties

```properties
# 数据库连接地址
spring.datasource.url=xxx 
# 数据库用户名
spring.datasource.username=xxx
# 数据库密码
spring.datasource.password=xxx
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
# 连接池中的最大空闲连接数
spring.datasource.max-idle=10
# 连接池在等待返回连接时，最长等待多少毫秒再抛出异常
spring.datasource.max-wait=10000
# 连接池里始终应该保持的最小连接数(用于DBCP和Tomcat连接池)
spring.datasource.min-idle=5
# 在连接池启动时要建立的连接数
spring.datasource.initial-size=5

server.port=8000
#server.session.timeout=10
server.tomcat.uri-encoding=UTF-8


# mybatis.config= classpath:mybatis-config.xml
# 对应映射的mapping的xml文件的路径
mybatis.mapperLocations=classpath:mapping/*.xml
# domain object's package
# 对应的bean类的路径
mybatis.typeAliasesPackage=包名.model
# handler's package
# mybatis.typeHandlersPackage=
# check the mybatis configuration exists
# mybatis.check-config-location=
# mode of execution. Default is SIMPLE
# mybatis.executorType=
```



