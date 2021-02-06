# 极客大学「Java进阶训练营-第0期」作业提交仓库
***
*作业提交地址：[https://github.com/JAVA-000/JAVA-000/issues](https://github.com/JAVA-000/JAVA-000/issues)*

## 毕业总结
1、(必做)分别用 100 个字以上的一段话，加上一幅图 (架构图或脑图)，总结自己对下列技术的关键点思考和经验认识:

PS:其他的后面复习总结完再补

- [Java进阶训练营总结文章总览](https://github.com/lw1243925457/SE-Notes/blob/master/profession/program/java/%E8%BF%9B%E9%98%B6%E7%9F%A5%E8%AF%86%E8%B7%AF%E5%BE%84/%E8%BF%9B%E9%98%B6%E7%9F%A5%E8%AF%86%E6%80%BB%E7%BB%93%E6%96%87%E7%AB%A0%E6%80%BB%E8%A7%88.md)

- [软件工程师知识体系脑图.xmind](https://github.com/lw1243925457/SE-Notes/blob/master/profession/%E8%BD%AF%E4%BB%B6%E5%B7%A5%E7%A8%8B%E5%B8%88%E7%9F%A5%E8%AF%86%E4%BD%93%E7%B3%BB%E8%84%91%E5%9B%BE.xmind)

- [x] 1) JVM：[ava训练营 模块一 JVM 总结 总览](https://github.com/lw1243925457/JAVA-000/blob/main/Week_02/README.md)
- [ ] 2) NIO: []()
- [ ] 3) 并发编程: []()
- [ ] 4) Spring 和 ORM 等框架: []()
- [ ] 5) MySQL 数据库和 SQL: []()
- [ ] 6) 分库分表: []()
- [ ] 7) RPC 和微服务: []()
- [ ] 8) 分布式缓存: []()
- [ ] 9) 分布式消息队列: []()

## 任务列表清单
### 1. JVM
常规

- [x] 1（选做）自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后自己分析一下对应的字节码，有问题群里讨论。

- [x] 2（必做）自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。

- [x] 3（必做）画一张图，展示 Xmx、Xms、Xmn、Meta、DirectMemory、Xss 这些内存参数的关系。

- [ ] 4（选做）检查一下自己维护的业务系统的 JVM 参数配置，用 jstat 和 jstack、jmap 查看一下详情，并且自己独立分析一下大概情况，思考有没有不合理的地方，如何改进。


进阶
从Classloader到模块化，动态加载的插件机制。

- [x] 1.10-使用自定义Classloader机制，实现xlass的加载：xlass是作业材料。
- [ ] 1.20-实现xlass打包的xar（类似class文件打包的jar）的加载：xar里是xlass。
- [ ] 2.30-基于自定义Classloader实现类的动态加载和卸载：需要设计加载和卸载。
- [ ] 3.30-基于自定义Classloader实现模块化机制：需要设计模块化机制。
- [ ] 4.30-使用xar作为模块，实现xar动态加载和卸载：综合应用前面的内容。

### 2. NIO
常规

- [x] 1.使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例。
- [x] 2.使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例。
- [ ] 3.（选做） 如果自己本地有可以运行的项目，可以按照 2 的方式进行演练。
- [x] 4.（必做） 根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 的总结，提交到 Github。
- [x] 5.（必做）整合你上次作业的 httpclient/okhttp；
- [x] 6.（选做）使用 netty 实现后端 http 访问（代替上一步骤）
- [x] 7.（必做）实现过滤器。
- [x] 8.（选做）实现路由。

进阶
实现一个http 文件服务器和一个ftp文件服务器。

- [x] 1.10-实现文件列表展示：http直接网页展示列表即可。ftp支持cd、ls命令。
- [ ] 2.20-实现文件上传下载：http上传不需要支持multi-part，直接post文件内容即可。ftp只需要支持主动模式或被动模式的一种。
- [ ] 3.30-支持断点续传：http下载需要实现range，上传需要自己设计服务器端的分片方式并记录。ftp需要实现retr，stor，rest命令。
- [ ] 4.30-实现多线程文件上传下载：基于断点续传，需考虑客户端分片方式，多线程调度。
- [ ] 5.30-实现爬虫爬取前面实现的服务器上所有文件：需要考虑html解析，记录多个文件的传输进度，位置等。

### 3. 并发
常规

- [ ] 1.（选做）把示例代码，运行一遍，思考课上相关的问题。也可以做一些比较。
- [x] 2.（必做）思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程？写出你的方法，越多越好，提交到 Github。
- [ ] 1.（选做）列举常用的并发操作 API 和工具类，简单分析其使用场景和优缺点。
- [ ] 2.（选做）请思考：什么是并发？什么是高并发？实现高并发高可用系统需要考虑哪些因素，对于这些你是怎么理解的？
- [ ] 3.（选做）请思考：还有哪些跟并发类似 / 有关的场景和问题，有哪些可以借鉴的解决办法。
- [x] 4.（必做）把多线程和并发相关知识带你梳理一遍，画一个脑图，截图上传到 Github 上。可选工具：xmind，百度脑图，wps，MindManage 或其他。

#### 3.1-侧重集合：

- [ ] 1.10-基于基本类型和数组，实现ArrayList/LinkedList，支持自动扩容和迭代器
- [ ] 2.20-基于基本类型和数组和List，HashMap/LinkedHashMap功能，处理hash冲突和扩容
- [ ] 3.30-考虑List和Map的并发安全问题，基于读写锁改进安全问题
- [ ] 4.30-考虑List和Map的并发安全问题，基于AQS改进安全问题
- [ ] 5.30-编写测试代码比较它们与java-util/JUC集合类的性能和并发安全性

#### 3.2-侧重应用：

- [ ] 1.10-根据课程提供的场景，实现一个订单处理Service，模拟处理100万订单：后面提供模拟数据。
- [ ] 2.20-使用多线程方法优化订单处理，对比处理性能
- [ ] 3.30-使用并发工具和集合类改进订单Service，对比处理性能
- [ ] 4.30-使用分布式集群+分库分表方式处理拆分订单，对比处理性能：第6模块讲解分库分表。
- [ ] 5.30-使用读写分离和分布式缓存优化订单的读性能：第6、8模块讲解读写分离和缓存。

### 4. 框架

- [x] 1.（选做）使 Java 里的动态代理，实现一个简单的 AOP。
- [x] 2.（必做）写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 Github。
- [x] 3.（选做）实现一个 Spring XML 自定义配置，配置一组 Bean，例如：Student/Klass/School。

- 4.（选做，会添加到高手附加题）[netty相关代码](https://github.com/lw1243925457/netty-gatewayDemo)
  - [x] 4.1 （挑战）讲网关的 frontend/backend/filter/router 线程池都改造成 Spring 配置方式；
    - [Intro to AspectJ](https://www.baeldung.com/aspectj)
  - [x] 4.2 （挑战）基于 AOP 改造 Netty 网关，filter 和 router 使用 AOP 方式实现；
  - [x] 4.3 （中级挑战）基于前述改造，将网关请求前后端分离，中级使用 JMS 传递消息；

  - [ ] 4.4 （中级挑战）尝试使用 ByteBuddy 实现一个简单的基于类的 AOP；
    - [Byte Buddy」20行代码实现AOP](https://zhuanlan.zhihu.com/p/84514959)
    - [raphw/byte-buddy](https://github.com/raphw/byte-buddy)

  - [ ] 4.5 （超级挑战）尝试使用 ByteBuddy 与 Instrument 实现一个简单 JavaAgent 实现无侵入下的 AOP。
    - [通过使用Byte Buddy，便捷地创建Java Agent](https://www.infoq.cn/article/Easily-Create-Java-Agents-with-ByteBuddy)

- [x] 1.（选做）总结一下，单例的各种写法，比较它们的优劣。
- [x] 2.（选做）maven/spring 的 profile 机制，都有什么用法？
- [x] 3.（选做）总结 Hibernate 与 MyBatis 的各方面异同点。
- [x] 4.（必做）给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。
- [ ] 5.（选做）学习 MyBatis-generator 的用法和原理，学会自定义 TypeHandler 处理复杂类型。
- [x] 6.（必做）研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：
  - [x] 1）使用 JDBC 原生接口，实现数据库的增删改查操作。
  - [x] 2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
  - [x] 3）配置 Hikari 连接池，改进上述操作。提交代码到 Github。

附加题（可以后面上完数据库的课再考虑做）：
- [ ] (挑战) 基于 AOP 和自定义注解，实现 @MyCache(60) 对于指定方法返回值缓存 60 秒。
  - [Java 自定义注解及使用场景](https://juejin.im/post/6844903949233815566)
  - [中级20 - 动态代理、AOP与Spring](https://g.yuque.com/wangpeng-iu4vg/adxz1q/by51uu)
  - [关于 Spring AOP (AspectJ) 你该知晓的一切](https://blog.csdn.net/javazejian/article/details/56267036)
- [ ] (挑战) 自定义实现一个数据库连接池，并整合 Hibernate/Mybatis/Spring/SpringBoot。
- [ ] (挑战) 基于 MyBatis 实现一个简单的分库分表 + 读写分离 + 分布式 ID 生成方案。

### Week06
Week06 作业题目（周四）：

- [ ] 1.选做）尝试使用 Lambda/Stream/Guava 优化之前作业的代码。
- [ ] 2.选做）尝试使用 Lambda/Stream/Guava 优化工作中编码的代码。
- [ ] 3.选做）根据课上提供的材料，系统性学习一遍设计模式，并在工作学习中思考如何用设计模式解决问题。
- [ ] 4.选做）根据课上提供的材料，深入了解 Google 和 Alibaba 编码规范，并根据这些规范，检查自己写代码是否符合规范，有什么可以改进的。

Week06 作业题目（周六）：

- [ ] 1.选做）基于课程中的设计原则和最佳实践，分析是否可以将自己负责的业务系统进行数据库设计或是数据库服务器方面的优化
- [x] 2.（必做）基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交 DDL 的 SQL 文件到 Github（后面 2 周的作业依然要是用到这个表结构）。
- [x] 3.（选做）尽可能多的从“常见关系数据库”中列的清单，安装运行，并使用上一题的 SQL 测试简单的增删改查。
- [x] 4.（选做）基于上一题，尝试对各个数据库测试 100 万订单数据的增删改查性能。
- [ ] 5.（选做）尝试对 MySQL 不同引擎下测试 100 万订单数据的增删改查性能。
- [ ] 6.（选做）模拟 1000 万订单数据，测试不同方式下导入导出（数据备份还原）MySQL 的速度，包括 jdbc 程序处理和命令行处理。思考和实践，如何提升处理效率。
- [ ] 7.（选做）对 MySQL 配置不同的数据库连接池（DBCP、C3P0、Druid、Hikari），测试增删改查 100 万次，对比性能，生成报告。

### Week07
Week07 作业题目（周四）：

- [ ] 1.（选做）用今天课上学习的知识，分析自己系统的 SQL 和表结构
- [x] 2.（必做）按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率
- [x] 3.（选做）按自己设计的表结构，插入 1000 万订单模拟数据，测试不同方式的插入效
- [ ] 4.（选做）使用不同的索引或组合，测试不同方式查询效率
- [ ] 5.（选做）调整测试数据，使得数据尽量均匀，模拟 1 年时间内的交易，计算一年的销售报表：销售总额，订单数，客单价，每月销售量，前十的商品等等（可以自己设计更多指标）
- [ ] 6.（选做）尝试自己做一个 ID 生成器（可以模拟 Seq 或 Snowflake）
- [ ] 7.（选做）尝试实现或改造一个非精确分页的程序

Week07 作业题目（周六）：
- [ ] 1.（选做）配置一遍异步复制，半同步复制、组复制
- [ ] 2.（必做）读写分离 - 动态切换数据源版本 1.0
  - [x] Raw-Jdbc
  - [ ] Mabatis
- [x] 3.（必做）读写分离 - 数据库框架版本 2.0
  - [x] Raw-Jdbc
  - [ ] Mabatis
- [x] 4.（选做）读写分离 - 数据库中间件版本 3.0
  - [x] Raw-Jdbc
  - [ ] Mabatis
- [ ] 5.（选做）配置 MHA，模拟 master 宕机
- [ ] 6.（选做）配置 MGR，模拟 master 宕机
- [ ] 7.（选做）配置 Orchestrator，模拟 master 宕机，演练 UI 调整拓扑结构

### Week08
- [ ] 1、（选做）分析前面作业设计的表，是否可以做垂直拆分
- [x] 2、（必做）设计对前面的订单表数据进行水平分库分表，拆分2个库，每个库16张表并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，上传到 Github
- [ ] 3、（选做）模拟1000万的订单单表数据，迁移到上面作业2的分库分表中
- [ ] 4、（选做）重新搭建一套4个库各64个表的分库分表，将作业2中的数据迁移到新分库

Week08 作业题目（周六）：
- [ ] 1.（选做）列举常见的分布式事务，简单分析其使用场景和优缺点。
- [x] 2.（必做）基于 hmily TCC 或 ShardingSphere 的 Atomikos XA 实现一个简单的分布式事务应用 demo（二选一），提交到 Github。
- [x] 3.（选做）基于 ShardingSphere narayana XA 实现一个简单的分布式事务 demo。
- [x] 4.（选做）基于 seata 框架实现 TCC 或 AT 模式的分布式事务 demo。
- [ ] 5.（选做☆）设计实现一个简单的 XA 分布式事务框架 demo，只需要能管理和调用 2 个 MySQL 的本地事务即可，不需要考虑全局事务的持久化和恢复、高可用等。
- [x] 6.（选做☆）设计实现一个 TCC 分布式事务框架的简单 Demo，需要实现事务管理器，不需要实现全局事务的持久化和恢复、高可用等。
- [ ] 7.（选做☆）设计实现一个 AT 分布式事务框架的简单 Demo，仅需要支持根据主键 id 进行的单个删改操作的 SQL 或插入操作的事务。

### Week09
周四：

- [ ] 1、（选做）实现简单的Protocol Buffer/Thrift/gRPC(选任一个)远程调用demo。
- [ ] 2、（选做）实现简单的WebService-Axis2/CXF远程调用demo。
- [x] 3、（必做）改造自定义RPC的程序，提交到github：
  - [x] 1）尝试将服务端写死查找接口实现类变成泛型和反射
  - [x] 2）尝试将客户端动态代理改成AOP，添加异常处理
  - [x] 3）尝试使用Netty+HTTP作为client端传输方式

4、（选做☆☆）升级自定义RPC的程序：
- [ ] 1）尝试使用压测并分析优化RPC性能
- [x] 2）尝试使用Netty+TCP作为两端传输方式
- [ ] 3）尝试自定义二进制序列化
- [ ] 4）尝试压测改进后的RPC并分析优化，有问题欢迎群里讨论
- [ ] 5）尝试将fastjson改成xstream
- [ ] 6）尝试使用字节码生成方式代替服务端反射

周六：

- [ ] 1、（选做）按课程第二部分练习各个技术点的应用。
- [ ] 2、（选做）按dubbo-samples项目的各个demo学习具体功能使用。
- [x] 3、（必做）结合dubbo+hmily，实现一个TCC外汇交易处理，代码提交到github：
  - 1）用户A的美元账户和人民币账户都在A库，使用1美元兑换7人民币；
  - 2）用户B的美元账户和人民币账户都在B库，使用7人民币兑换1美元；
  - 3）设计账户表，冻结资产表，实现上述两个本地事务的分布式事务。

4、（挑战☆☆）尝试扩展Dubbo
- [ ] 1）基于上次作业的自定义序列化，实现Dubbo的序列化扩展；
- [ ] 2）基于上次作业的自定义RPC，实现Dubbo的RPC扩展；
- [ ] 3）在Dubbo的filter机制上，实现REST权限控制，可参考dubbox；
- [ ] 4）实现一个自定义Dubbo的Cluster/Loadbalance扩展，如果一分钟内调用某个服务提供者超过10次，则拒绝提供服务直到下一分钟；
- [ ] 5）整合Dubbo+Sentinel，实现限流功能；
- [ ] 6）整合Dubbo与Skywalking，实现全链路性能监控。

### week10
- [x] 1、（选做）rpcfx1.1: 给自定义RPC实现简单的分组(group)和版本(version)。
- [x] 2、（选做）rpcfx2.0: 给自定义RPC实现：
  - [x] 1）基于zookeeper的注册中心，消费者和生产者可以根据注册中心查找可用服务进行调用(直接选择列表里的最后一个)。
  - [x] 2）当有生产者启动或者下线时，通过zookeeper通知并更新各个消费者，使得各个消费者可以调用新生产者或者不调用下线生产者。

- [ ] 3、（挑战☆）在2.0的基础上继续增强rpcfx实现：
  - [ ] 1）3.0: 实现基于zookeeper的配置中心，消费者和生产者可以根据配置中心配置参数（分组，版本，线程池大小等）。
  - [ ] 2）3.1：实现基于zookeeper的元数据中心，将服务描述元数据保存到元数据中心。
  - [ ] 3）3.2：实现基于etcd/nacos/apollo等基座的配置/注册/元数据中心。

- [x] 4、（挑战☆☆）在3.2的基础上继续增强rpcfx实现：
  - [x] 1）4.0：实现基于tag的简单路由；
  - [x] 2）4.1：实现基于Weight/ConsistentHash的负载均衡;
  - [x] 3）4.2：实现基于IP黑名单的简单流控；
  - [x] 4）4.3：完善RPC框架里的超时处理，增加重试参数；

- [ ] 5、（挑战☆☆☆）在4.3的基础上继续增强rpcfx实现：
  - [ ] 1）5.0：实现利用HTTP头跨进程传递Context参数（隐式传参）；
  - [ ] 2）5.1：实现消费端mock一个指定对象的功能（Mock功能）；
  - [ ] 3）5.2：实现消费端可以通过一个泛化接口调用不同服务（泛化调用）；
  - [ ] 4）5.3：实现基于Weight/ConsistentHash的负载均衡;
  - [ ] 5）5.4：实现基于单位时间调用次数的流控，可以基于令牌桶等算法；

- [ ] 6、（挑战☆☆☆☆）6.0：压测，并分析调优5.4版本。

### Week11
- [x] 1、（选做）命令行下练习操作Redis的各种基本数据结构和命令。
- [ ] 2、（选做）分别基于jedis，RedisTemplate，Lettuce，Redission实现redis基本操作的demo，可以使用spring-boot集成上述工具。

- [ ] 3、（选做）spring集成练习:
  - [ ] 1）实现update方法，配合@CachePut
  - [ ] 2）实现delete方法，配合@CacheEvict
  - [ ] 3）将示例中的spring集成Lettuce改成jedis或redisson。

- [x] 4、（必做）基于Redis封装分布式数据操作：
  - [x] 1）在Java中实现一个简单的分布式锁；
  - [x] 2）在Java中实现一个分布式计数器，模拟减库存

- [x] 5、基于Redis的PubSub实现订单异步处理

- [x] 1、（挑战☆）基于其他各类场景，设计并在示例代码中实现简单demo：
  - [x] 1）实现分数排名或者排行榜；
  - [x] 2）实现全局ID生成；
  - [x] 3）基于Bitmap实现id去重；
  - [x] 4）基于HLL实现点击量计数。
  - [x] 5）以redis作为数据库，模拟使用lua脚本实现前面课程的外汇交易事务。

- [ ] 2、（挑战☆☆）升级改造项目：
  - [ ] 1）实现guava cache的spring cache适配；
  - [ ] 2）替换jackson序列化为fastjson或者fst，kryo；
  - [ ] 3）对项目进行分析和性能调优。

- [ ] 3、（挑战☆☆☆）以redis作为基础实现上个模块的自定义rpc的注册中心。

### Week12
- [x] 1.（必做）配置 redis 的主从复制，sentinel 高可用，Cluster 集群
- [x] 2.（选做）练习示例代码里下列类中的作业题: 08cache/redis/src/main/java/io/kimmking/cache/RedisApplication.java
- [x] 3.（选做☆）练习 redission 的各种功能
- [x] 4.（选做☆☆）练习 hazelcast 的各种功能
- [x] 5.（选做☆☆☆）搭建 hazelcast 3 节点集群，写入 100 万数据到一个 map，模拟和演 示高可用

### Week13
- [x] 1、（必做）搭建ActiveMQ服务，基于JMS，写代码分别实现对于queue和topic的消息生产和消费，代码提交到github。

- [x] 2、（选做）基于数据库的订单表，模拟消息队列处理订单：
  - [x] 1）一个程序往表里写新订单，标记状态为未处理(status=0);
  - [x] 2）另一个程序每隔100ms定时从表里读取所有status=0的订单，打印一下订单数据，然后改成完成status=1；
  - [x] 3）（挑战☆）考虑失败重试策略，考虑多个消费程序如何协作。

- [x] 3、（选做）将上述订单处理场景，改成使用ActiveMQ发送消息处理模式。

- [x] 4、（挑战☆☆）搭建ActiveMQ的network集群和master-slave主从结构。

- [x] 5、（挑战☆☆☆）基于ActiveMQ的MQTT实现简单的聊天功能或者Android消息推送。

- [x] 6、（挑战☆）创建一个RabbitMQ，用Java代码实现简单的AMQP协议操作。

- [ ] 7、（挑战☆☆）搭建RabbitMQ集群，重新实现前面的订单处理。

- [ ] 8、（挑战☆☆☆）使用Apache Camel打通上述ActiveMQ集群和RabbitMQ集群，实现所有写入到ActiveMQ上的一个队列q24的消息，自动转发到RabbitMQ。

- [ ] 9、（挑战☆☆☆）压测ActiveMQ和RabbitMQ的性能


- [x] 1、（必做）搭建一个3节点Kafka集群，测试功能和性能；实现spring kafka下对kafka集群的操作，将代码提交到github。

- [x] 2、（选做）安装kafka-manager工具，监控kafka集群状态。

- [x] 3、（挑战☆）演练本课提及的各种生产者和消费者特性。

- [x] 4、（挑战☆☆☆）Kafka金融领域实战：在证券或者外汇、数字货币类金融核心交易系统里，对于订单的处理，大概可以分为收单、定序、撮合、清算等步骤。其中我们一般可以用mq来实现订单定序，然后将订单发送给撮合模块。
  - [x] 1）收单：请实现一个订单的rest接口，能够接收一个订单Order对象；
  - [x] 2）定序：将Order对象写入到kafka集群的order.usd2cny队列，要求数据有序并且不丢失；
  - [x] 3）撮合：模拟撮合程序（不需要实现撮合逻辑），从kafka获取order数据，并打印订单信息，要求可重放, 顺序消费, 消息仅处理一次。。

### Week14
- [x] 1、基于内存Queue实现生产和消费API（已经完成）
  - [x] 1）创建内存Queue，作为底层消息存储
  - [x] 2）定义Topic，支持多个Topic
  - [x] 3）定义Producer，支持Send消息
  - [x] 4）定义Consumer，支持Poll消息

- [x] 2、去掉内存Queue，设计自定义Queue，实现消息确认和消费offset
  - [x] 1）自定义内存Message数组模拟Queue。
  - [x] 2）使用指针记录当前消息写入位置。
  - [x] 3）对于每个命名消费者，用指针记录消费位置。

- [x] 3、拆分broker和client(包括producer和consumer)
  - [x] 1）将Queue保存到web server端
  - [x] 2）设计消息读写API接口，确认接口，提交offset接口
  - [x] 3）producer和consumer通过httpclient访问Queue
  - [x] 4）实现消息确认，offset提交
    - [x] 单机
    - [ ] 集群
  - [x] 5）实现consumer从offset增量拉取

- [ ] 1）考虑实现消息过期，消息重试，消息定时投递等策略
- [ ] 2）考虑批量操作，包括读写，可以打包和压缩
- [ ] 2）考虑消息清理策略，包括定时清理，按容量清理等
- [ ] 3）考虑消息持久化，存入数据库，或WAL日志文件，或BookKeeper
- [ ] 4）考虑将spring mvc替换成netty下的tcp传输协议

- [ ] 5、对接各种技术（各条之间没有关系，可以任意选择实现）
  - [ ] 1）考虑封装 JMS 1.1 接口规范
  - [ ] 2）考虑实现 STOMP 消息规范
  - [ ] 3）考虑实现消息事务机制与事务管理器
  - [ ] 4）对接Spring
  - [ ] 5）对接Camel或Spring Integration
  - [ ] 6）优化内存和磁盘的使用

### Week15
- [ ] 秒杀系统的设计和实现Demo