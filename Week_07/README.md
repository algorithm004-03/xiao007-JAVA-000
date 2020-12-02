# 第七周学习笔记
***
## 作业一
### 读写分离 - 动态 切换数据源版本 1.0
&ensp;&ensp;&ensp;&ensp;完成情况说明：

- 配置了三个数据库：1主2从
  - [MySQL docker配置记录](./MySQLDocker主从配置.md)
- service中的insert注入主库，query注入从库
- 使用注解实现不同数据库源的注入
- 简单的实现了从库访问的负载均衡

&ensp;&ensp;&ensp;&ensp;作业详情：

- 作业详细代码地址：https://github.com/lw1243925457/JAVA-000/tree/main/code/src/main/java/com/example/demo/database
- 测试运行代码：https://github.com/lw1243925457/JAVA-000/tree/main/code/src/test/java/com/example/demo/database/service/OrderServiceImplTest.java

## 作业二
### 读写分离 - 数据库框架版本 2.0
&ensp;&ensp;&ensp;&ensp;简单的直接使用配置文件配置，在测试代码中注入后直接使用SQL进行执行，没有结合ORM

- 代码地址：https://github.com/lw1243925457/JAVA-000/tree/main/code/src/main/java/com/example/demo/shardingsphere/raw/jdbc/ShardingMasterSlaveDataSource.java
- 测试类：https://github.com/lw1243925457/JAVA-000/tree/main/code/src/test/java/com/example/demo/shardingsphere/raw/jdbc/ShardingMasterSlaveDataSourceTest.java

### 参考链接
- [USE SPRING BOOT STARTER](https://shardingsphere.apache.org/document/current/en/user-manual/shardingsphere-jdbc/usage/sharding/spring-boot-starter/)
- [READ-WRITE SPLITTING](https://shardingsphere.apache.org/document/legacy/3.x/document/en/manual/sharding-jdbc/usage/read-write-splitting/)
- [ShardingSphere入门实战(1)-Sharding-JDBC使用](https://www.cnblogs.com/wuyongyin/p/13336373.html)

## 作业三
### 按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率
&ensp;&ensp;&ensp;&ensp;测试了两种，一个是关闭自动提交，一百万的数据弄好后一起提交；一种是一条一条的提交插入

&ensp;&ensp;&ensp;&ensp;通过测试，批量的插入是非常快的；下面的两个测试都写了存储过程，脚本在下方链接：

- [fillTest.sql](./fillTest.sql)

- 关闭自动提交，一百万的数据弄好后一起提交：completed in 2 m 29 s 195 ms
- 一条一条的提交插入：之前测试过插入10万的，要差一个多小时左右，这里只知道会很久吧，就不测具体数据了

## 作业四
### 选做）读写分离 - 数据库中间件版本 3.0
&ensp;&ensp;&ensp;&ensp;使用ShardingSphere Proxy实现读写分离，相关的使用配置记录文档如下：

- [Sharding-Sphere Proxy 分库分表 简单示例](https://github.com/lw1243925457/SE-Notes/blob/master/profession/program/java/shardingsphere/ShardingSphereProxy%E5%88%86%E5%BA%93%E5%88%86%E8%A1%A8.md)

- 测试代码地址(直接使用jdbc，结合ORM和mybatis感觉改下配置就行了，后面再补上）：https://github.com/lw1243925457/JAVA-000/tree/main/homework/week0802/

## 学习过程中的总结记录
- [MysqlDocker主从配置.md](https://github.com/lw1243925457/SE-Notes/blob/master/profession/system/software/MySQL/MysqlDocker%E4%B8%BB%E4%BB%8E%E9%85%8D%E7%BD%AE.md)
- [shardingsphere-raw-jdbc.md](https://github.com/lw1243925457/SE-Notes/blob/master/profession/program/java/shardingsphere/shardingsphere-raw-jdbc.md)
- [shardingSphere-proxy主从读写.md](https://github.com/lw1243925457/SE-Notes/blob/master/profession/program/java/shardingsphere/shardingSphere-proxy%E4%B8%BB%E4%BB%8E%E8%AF%BB%E5%86%99.md)
- [ShardingSphereProxy分库分表.md](https://github.com/lw1243925457/SE-Notes/blob/master/profession/program/java/shardingsphere/ShardingSphereProxy%E5%88%86%E5%BA%93%E5%88%86%E8%A1%A8.md)