# 第十一周学习笔记
***
### 1.（选做）命令行下练习操作Redis的各种基本数据结构和命令。
- [x]  1、（选做）命令行下练习操作Redis的各种基本数据结构和命令。

通过下面的链接（有时候感觉很慢）和书籍能快速查询和了解相关功能和命令：

参考链接：[Redis 命令参考](http://redisdoc.com/index.html)

参考书籍：[redis命令参考手册.pdf](https://github.com/ithup/Redis/blob/master/redis%E5%91%BD%E4%BB%A4%E5%8F%82%E8%80%83%E6%89%8B%E5%86%8C.pdf)

###  2、（选做）分别基于jedis，RedisTemplate，Lettuce，Redission实现redis基本操作的demo，可以使用spring-boot集成上述工具

###  3、（选做）spring集成练习
- [ ] 3、（选做）spring集成练习:
  - [ ] 1）实现update方法，配合@CachePut
  - [ ] 2）实现delete方法，配合@CacheEvict
  - [ ] 3）将示例中的spring集成Lettuce改成jedis或redisson

### 4.基于Redis封装分布式数据操作：
- [x] （必做）基于Redis封装分布式数据操作：
 - [x] 1）在Java中实现一个简单的分布式锁；
 - [x] 2）在Java中实现一个分布式计数器，模拟减库存。

代码及详情地址：[redis-lock](https://github.com/lw1243925457/JAVA-000/tree/main/homework/redis/redis-lock)

### 5.基于Redis的PubSub实现订单异步处理
- [x] 基于Redis的PubSub实现订单异步处理

代码及详情地址：[redis-pubsub](https://github.com/lw1243925457/JAVA-000/tree/main/homework/redis/redis-pubsub)

### 1.（挑战☆）基于其他各类场景，设计并在示例代码中实现简单demo
- [x] 1）实现分数排名或者排行榜；
- [x] 2）实现全局ID生成；
- [x] 3）基于Bitmap实现id去重；
- [x] 4）基于HLL实现点击量计数。
- [x] 5）以redis作为数据库，模拟使用lua脚本实现前面课程的外汇交易事务

代码及详情地址：[redis-scene](https://github.com/lw1243925457/JAVA-000/tree/main/homework/redis/redis-scene)

### 2、（挑战☆☆）升级改造项目：
- [ ] 2、（挑战☆☆）升级改造项目：
  - [ ] 1）实现guava cache的spring cache适配；
  - [ ] 2）替换jackson序列化为fastjson或者fst，kryo；
  - [ ] 3）对项目进行分析和性能调优

### 3、（挑战☆☆☆）以redis作为基础实现上个模块的自定义rpc的注册中心
- [ ] 3、（挑战☆☆☆）以redis作为基础实现上个模块的自定义rpc的注册中心。


## 其他
### Redis docker 的简便使用
```bash
# 下载并运行redis，映射到宿主机端口
docker run -dit --name redis -p 6379:6379 redis

# 查看运行日志
docker logs -f redis

# 使用的客户端连接redis
docker exec -ti redis redis-cli -h 192.168.101.104 -p 6379
```