# 第十一周学习笔记
***
### 基于Redis封装分布式数据操作：
- [x] （必做）基于Redis封装分布式数据操作：
 - [x] 1）在Java中实现一个简单的分布式锁；
 - [x] 2）在Java中实现一个分布式计数器，模拟减库存。

代码及详情地址：[redis-lock](https://github.com/lw1243925457/JAVA-000/tree/main/homework/redis/redis-lock)

### 基于Redis的PubSub实现订单异步处理
- [x] 基于Redis的PubSub实现订单异步处理

代码及详情地址：[redis-pubsub](https://github.com/lw1243925457/JAVA-000/tree/main/homework/redis/redis-pubsub)


### （挑战☆）基于其他各类场景，设计并在示例代码中实现简单demo
- [x] 1）实现分数排名或者排行榜；
- [x] 2）实现全局ID生成；
- [x] 3）基于Bitmap实现id去重；
- [x] 4）基于HLL实现点击量计数。
- [x] 5）以redis作为数据库，模拟使用lua脚本实现前面课程的外汇交易事务

代码及详情地址：[redis-scene](https://github.com/lw1243925457/JAVA-000/tree/main/homework/redis/redis-scene)


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