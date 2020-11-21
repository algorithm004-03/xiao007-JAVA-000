# 极客大学「Java进阶训练营-第0期」作业提交仓库
***
*作业提交地址：[https://github.com/JAVA-000/JAVA-000/issues](https://github.com/JAVA-000/JAVA-000/issues)*

## 仓库目录结构说明

1. `week01/` 代表第一周作业提交目录，以此类推。
2. 请在对应周的目录下新建或修改自己的代码作业。
2. 每周均有一个 `REDAME.md` 文档，你可以将自己当周的学习心得以及做题过程中的思考记录在该文档中。

## 作业提交规则
 
1. 先将本仓库 Fork 到自己 GitHub 账号下。
2. 将 Fork 后的仓库 Clone 到本地，然后在本地仓库中对应周的目录下新建或修改自己的代码作业，当周的学习总结写在对应周的README.md文件里。
3. 在本地仓库完成作业后，push 到自己的 GitHub 远程仓库。
4. 最后将远程仓库中当周的作业链接，按格式贴到班级仓库对应学习周的issue下面。
5. 提交issue请务必按照规定格式进行提交，否则作业统计工具将抓取不到你的作业提交记录。 

详细的作业提交流程可以查阅：https://shimo.im/docs/m5rtM8K8rNsjw5jk/ 


## 注意事项

 如果对 Git 和 GitHub 不太了解，请参考 [Git 官方文档](https://git-scm.com/book/zh/v2) 或者极客时间的[《玩转 Git 三剑客》](https://time.geekbang.org/course/intro/145)视频课程。


## 任务列表清单

### 1. JVM
常规

- ~~1.（选做）自己写一个简单的 Hello.java，里面需要涉及基本类型，四则运行，if 和 for，然后自己分析一下对应的字节码，有问题群里讨论。~~
- ~~2.（必做）自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。~~
- ~~3.（必做）画一张图，展示 Xmx、Xms、Xmn、Meta、DirectMemory、Xss 这些内存参数的关系。~~
- 4.（选做）检查一下自己维护的业务系统的 JVM 参数配置，用 jstat 和 jstack、jmap 查看一下详情，并且自己独立分析一下大概情况，思考有没有不合理的地方，如何改进。


进阶
从Classloader到模块化，动态加载的插件机制。

- ~~1. 10-使用自定义Classloader机制，实现xlass的加载：xlass是作业材料。~~
- 1. 20-实现xlass打包的xar（类似class文件打包的jar）的加载：xar里是xlass。
- 2. 30-基于自定义Classloader实现类的动态加载和卸载：需要设计加载和卸载。
- 3. 30-基于自定义Classloader实现模块化机制：需要设计模块化机制。
- 4. 30-使用xar作为模块，实现xar动态加载和卸载：综合应用前面的内容。

### 2. NIO
常规

~~1.使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例。~~
~~2.使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例。~~
~~3.（选做） 如果自己本地有可以运行的项目，可以按照 2 的方式进行演练。~~
~~4.（必做） 根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 的总结，提交到 Github。~~
~~5.（必做）整合你上次作业的 httpclient/okhttp；~~
~~6.（选做）使用 netty 实现后端 http 访问（代替上一步骤）~~
~~7.（必做）实现过滤器。~~
~~8.（选做）实现路由。~~

进阶
实现一个http 文件服务器和一个ftp文件服务器。

- 1. 10-实现文件列表展示：http直接网页展示列表即可。ftp支持cd、ls命令。
- 2. 20-实现文件上传下载：http上传不需要支持multi-part，直接post文件内容即可。ftp只需要支持主动模式或被动模式的一种。
- 3. 30-支持断点续传：http下载需要实现range，上传需要自己设计服务器端的分片方式并记录。ftp需要实现retr，stor，rest命令。
- 4. 30-实现多线程文件上传下载：基于断点续传，需考虑客户端分片方式，多线程调度。
- 5. 30-实现爬虫爬取前面实现的服务器上所有文件：需要考虑html解析，记录多个文件的传输进度，位置等。

### 3. 并发
常规

- 1.（选做）把示例代码，运行一遍，思考课上相关的问题。也可以做一些比较。
- ~~2.（必做）思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程？写出你的方法，越多越好，提交到 Github。~~
- 1.（选做）列举常用的并发操作 API 和工具类，简单分析其使用场景和优缺点。
- 2.（选做）请思考：什么是并发？什么是高并发？实现高并发高可用系统需要考虑哪些因素，对于这些你是怎么理解的？
- 3.（选做）请思考：还有哪些跟并发类似 / 有关的场景和问题，有哪些可以借鉴的解决办法。
- ~~4.（必做）把多线程和并发相关知识带你梳理一遍，画一个脑图，截图上传到 Github 上。可选工具：xmind，百度脑图，wps，MindManage 或其他。~~

#### 3.1-侧重集合：

- 1. 10-基于基本类型和数组，实现ArrayList/LinkedList，支持自动扩容和迭代器
- 2. 20-基于基本类型和数组和List，HashMap/LinkedHashMap功能，处理hash冲突和扩容
- 3. 30-考虑List和Map的并发安全问题，基于读写锁改进安全问题
- 4. 30-考虑List和Map的并发安全问题，基于AQS改进安全问题
- 5. 30-编写测试代码比较它们与java-util/JUC集合类的性能和并发安全性

#### 3.2-侧重应用：

- 1. 10-根据课程提供的场景，实现一个订单处理Service，模拟处理100万订单：后面提供模拟数据。
- 2. 20-使用多线程方法优化订单处理，对比处理性能
- 3. 30-使用并发工具和集合类改进订单Service，对比处理性能
- 4. 30-使用分布式集群+分库分表方式处理拆分订单，对比处理性能：第6模块讲解分库分表。
- 5. 30-使用读写分离和分布式缓存优化订单的读性能：第6、8模块讲解读写分离和缓存。

### 4. 框架

- ~~1.（选做）使 Java 里的动态代理，实现一个简单的 AOP。~~
- ~~2.（必做）写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 Github。~~
- ~~3.（选做）实现一个 Spring XML 自定义配置，配置一组 Bean，例如：Student/Klass/School。~~

- 4.（选做，会添加到高手附加题）[netty相关代码](https://github.com/lw1243925457/netty-gatewayDemo)
  - ~~4.1 （挑战）讲网关的 frontend/backend/filter/router 线程池都改造成 Spring 配置方式；~~
    - [Intro to AspectJ](https://www.baeldung.com/aspectj)
  - ~~4.2 （挑战）基于 AOP 改造 Netty 网关，filter 和 router 使用 AOP 方式实现；~~
  - ~~4.3 （中级挑战）基于前述改造，将网关请求前后端分离，中级使用 JMS 传递消息；~~

  - 4.4 （中级挑战）尝试使用 ByteBuddy 实现一个简单的基于类的 AOP；
    - [Byte Buddy」20行代码实现AOP](https://zhuanlan.zhihu.com/p/84514959)
    - [raphw/byte-buddy](https://github.com/raphw/byte-buddy)

  - 4.5 （超级挑战）尝试使用 ByteBuddy 与 Instrument 实现一个简单 JavaAgent 实现无侵入下的 AOP。
    - [通过使用Byte Buddy，便捷地创建Java Agent](https://www.infoq.cn/article/Easily-Create-Java-Agents-with-ByteBuddy)

- ~~1.（选做）总结一下，单例的各种写法，比较它们的优劣。~~
- ~~2.（选做）maven/spring 的 profile 机制，都有什么用法？~~
- ~~3.（选做）总结 Hibernate 与 MyBatis 的各方面异同点。~~
- ~~4.（必做）给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。~~
- 5.（选做）学习 MyBatis-generator 的用法和原理，学会自定义 TypeHandler 处理复杂类型。
- ~~6.（必做）研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：~~
- ~~1）使用 JDBC 原生接口，实现数据库的增删改查操作。~~
- ~~2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。~~
- ~~3）配置 Hikari 连接池，改进上述操作。提交代码到 Github。~~

附加题（可以后面上完数据库的课再考虑做）：
- (挑战) 基于 AOP 和自定义注解，实现 @MyCache(60) 对于指定方法返回值缓存 60 秒。
  - [Java 自定义注解及使用场景](https://juejin.im/post/6844903949233815566)
  - [中级20 - 动态代理、AOP与Spring](https://g.yuque.com/wangpeng-iu4vg/adxz1q/by51uu)
  - [关于 Spring AOP (AspectJ) 你该知晓的一切](https://blog.csdn.net/javazejian/article/details/56267036)
- (挑战) 自定义实现一个数据库连接池，并整合 Hibernate/Mybatis/Spring/SpringBoot。
- (挑战) 基于 MyBatis 实现一个简单的分库分表 + 读写分离 + 分布式 ID 生成方案。

## 其他
- [https://www.yuque.com/docs/share/eedab2ad-6486-4690-b048-d9bb9a6b4a4a]()


