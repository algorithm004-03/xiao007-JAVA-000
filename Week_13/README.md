# 第十三周学习笔记
***
## 作业详情(做完的点击后就跳转到作业详情)
- [x] [1、（必做）搭建ActiveMQ服务，基于JMS，写代码分别实现对于queue和topic的消息生产和消费，代码提交到github](https://github.com/lw1243925457/JAVA-000/tree/main/homework/mq/jms-activemp)

- [x] [2、（选做）基于数据库的订单表，模拟消息队列处理订单：](https://github.com/lw1243925457/JAVA-000/tree/main/homework/mq/mysql-mq-order)
  - [x] 1）一个程序往表里写新订单，标记状态为未处理(status=0)
  - [x] 2）另一个程序每隔100ms定时从表里读取所有status=0的订单，打印一下订单数据，然后改成完成status=1
  - [x] 3）（挑战☆）考虑失败重试策略，考虑多个消费程序如何协作

- [x] [3、（选做）将上述订单处理场景，改成使用ActiveMQ发送消息处理模式。](https://github.com/lw1243925457/JAVA-000/tree/main/homework/mq/active-mq-order)

- [x] 4、（挑战☆☆）搭建ActiveMQ的network集群和master-slave主从结构
  - [docker activemq 集群参考链接](https://blog.csdn.net/kang389110772/article/details/78270875)
  - [docker activemq 主从参考链接](https://www.cnblogs.com/MrYangToBlog/p/dockerToActiveMQ.html)

- [x] [5、（挑战☆☆☆）基于ActiveMQ的MQTT实现简单的聊天功能或者Android消息推送](https://github.com/lw1243925457/JAVA-000/tree/main/homework/mq/activemq-mqtt)
  - 实现了简单的消息推送

- [x] [6、（挑战☆）创建一个RabbitMQ，用Java代码实现简单的AMQP协议操作](https://github.com/lw1243925457/JAVA-000/tree/main/homework/mq/rabbitmq-springboot)

- [ ] 7、（挑战☆☆）搭建RabbitMQ集群，重新实现前面的订单处理。

- [ ] 8、（挑战☆☆☆）使用Apache Camel打通上述ActiveMQ集群和RabbitMQ集群，实现所有写入到ActiveMQ上的一个队列q24的消息，自动转发到RabbitMQ。

- [ ] 9、（挑战☆☆☆）压测ActiveMQ和RabbitMQ的性能


- [x] [1、（必做）搭建一个3节点Kafka集群，测试功能和性能；实现spring kafka下对kafka集群的操作，将代码提交到github](https://github.com/lw1243925457/JAVA-000/tree/main/homework/mq/kakfa-example)

- [x] 2、（选做）安装kafka-manager工具，监控kafka集群状态。
  - docker run -dit -p 9000:9000 -e ZK_HOSTS="192.168.101.104:2181" hlebalbau/kafka-manager:stable
  - 使用上面的docker命令启动后，访问: http://localhost:9000/ , 点击添加cluster，输入前两个（名称和zk地址），保存即可

- [x] 3、（挑战☆）演练本课提及的各种生产者和消费者特性
  - 参考下面的链接搭建集群环境，然后输入各种命令就行，就不进行详细的记录了
  - [Kafka集群搭建（使用kafka自带的zookeeper](https://blog.csdn.net/qq_34834325/article/details/78743490)
  - [Windows10 Kafka Docker 集群搭建](https://github.com/lw1243925457/SE-Notes/blob/master/profession/system/software/kafka/kafkaDocker%E9%9B%86%E7%BE%A4%E6%90%AD%E5%BB%BA.md)

- [x] [4、（挑战☆☆☆）Kafka金融领域实战：在证券或者外汇、数字货币类金融核心交易系统里，对于订单的处理，大概可以分为收单、定序、撮合、清算等步骤。其中我们一般可以用mq来实现订单定序，然后将订单发送给撮合模块](https://github.com/lw1243925457/JAVA-000/tree/main/homework/mq/finance-order)
  - [x] 1）收单：请实现一个订单的rest接口，能够接收一个订单Order对象；
  - [x] 2）定序：将Order对象写入到kafka集群的order.usd2cny队列，要求数据有序并且不丢失；
  - [x] 3）撮合：模拟撮合程序（不需要实现撮合逻辑），从kafka获取order数据，并打印订单信息，要求可重放, 顺序消费, 消息仅处理一次。