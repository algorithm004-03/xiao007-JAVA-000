# Mybatis 二级缓存简单示例
***
## 简介
简单接收Mybatis中二级缓存的使用示例

## 概览
主要部分如下：

- 引入Maven依赖
- SpringBoot配置文件配置、建库与初始化SQL语句
- 实体类与Mapper编写
- 测试

### Maven依赖
完整依赖大致如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    ......
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.11</version>
        </dependency>

        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.2.0</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    ......
</project>
```

### SpringBoot配置文件配置、建库与初始化SQL语句
配置文件如下：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&allowPublicKeyRetrieval=true&useSSL=false
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
  sql:
    init:
      schema-locations: classpath:mybatis/schema-mysql.sql
      data-locations: classpath:mybatis/data-mysql.sql

mybatis:
  configuration:
    cache-enabled: true
  mapper-locations: classpath:mybatis/mapper/*.xml

logging.level.com.example.cache.mapper: debug
```

建库DDL：

```mysql
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id BIGINT(20) NOT NULL COMMENT '主键ID',
    name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    age INT(11) NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);
```

数据初始化DDL：

```mysql
DELETE FROM `user`;

INSERT INTO `user` (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');
```

### 实体类与Mapper编写
User实体类：

```java
import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
```

UserMapper.java

```java
import com.example.cache.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<User> select();
}
```

UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.example.cache.mapper.UserMapper"><cache/>
    <select id="select" resultType="com.example.cache.entity.User">
        select * from `user`;
    </select>
</mapper>
```

### 测试
测试代码如下：

```java
import com.example.cache.entity.User;
import com.example.cache.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectOneCache() {
        List<User> users = userMapper.select();
        Assertions.assertEquals(5, users.size());
        users.forEach(System.out::println);

        users = userMapper.select();
        Assertions.assertEquals(5, users.size());
        users.forEach(System.out::println);
    }
}
```

输出如下：

```shell
2021-06-08 22:38:48.925 DEBUG 7296 --- [           main] c.e.cache.mapper.UserMapper.select       : ==>  Preparing: select * from `user`;
2021-06-08 22:38:48.950 DEBUG 7296 --- [           main] c.e.cache.mapper.UserMapper.select       : ==> Parameters: 
2021-06-08 22:38:48.979 DEBUG 7296 --- [           main] c.e.cache.mapper.UserMapper.select       : <==      Total: 5
User(id=1, name=Jone, age=18, email=test1@baomidou.com)
User(id=2, name=Jack, age=20, email=test2@baomidou.com)
User(id=3, name=Tom, age=28, email=test3@baomidou.com)
User(id=4, name=Sandy, age=21, email=test4@baomidou.com)
User(id=5, name=Billie, age=24, email=test5@baomidou.com)
2021-06-08 22:38:48.995  WARN 7296 --- [           main] o.apache.ibatis.io.SerialFilterChecker   : As you are using functionality that deserializes object streams, it is recommended to define the JEP-290 serial filter. Please refer to https://docs.oracle.com/pls/topic/lookup?ctx=javase15&id=GUID-8296D8E8-2B93-4B9A-856E-0A65AF9B8C66
2021-06-08 22:38:48.997 DEBUG 7296 --- [           main] com.example.cache.mapper.UserMapper      : Cache Hit Ratio [com.example.cache.mapper.UserMapper]: 0.5
User(id=1, name=Jone, age=18, email=test1@baomidou.com)
User(id=2, name=Jack, age=20, email=test2@baomidou.com)
User(id=3, name=Tom, age=28, email=test3@baomidou.com)
User(id=4, name=Sandy, age=21, email=test4@baomidou.com)
User(id=5, name=Billie, age=24, email=test5@baomidou.com)
```

可以看到第二次查询没有走SQL查询，直接从Cache中取得了结果

## 总结
一篇简单的Mybatis二级缓存的示例

从中可以看到，SpringBoot Mybatis是默认开启二级缓存的，其他更多的使用，如缓存失效等，后面再探索

## 参考链接
- [junit4 maven install](https://github.com/junit-team/junit4/wiki/Download-and-Install)
- [Mybatis plus 官方中文快速入门](https://baomidou.com/guide/quick-start.html#%E5%BC%80%E5%A7%8B%E4%BD%BF%E7%94%A8)
- [SpringBoot整合Mybatis-Plus 实战之Mybatis-Plus的一级缓存、二级缓存详解](https://juejin.cn/post/6902278477626474504)
- [MyBatis-Spring-Boot-Starter](https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)