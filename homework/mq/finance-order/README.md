# Kafka金融领域实战
***
## 作业详情
&ensp;&ensp;&ensp;&ensp;在证券或者外汇、数字货币类金融核心交易系统里，对于订单的处理，大概可以分为收单、定序、撮合、清算等步骤。其中我们一般可以用mq来实现订单定序，然后将订单发送给撮合模块。

- [ ] 1）收单：请实现一个订单的rest接口，能够接收一个订单Order对象；
- [ ] 2）定序：将Order对象写入到kafka集群的order.usd2cny队列，要求数据有序并且不丢失；
- [ ] 3）撮合：模拟撮合程序（不需要实现撮合逻辑），从kafka获取order数据，并打印订单信息，要求可重放, 顺序消费, 消息仅处理一次

## 工程说明
&ensp;&ensp;&ensp;&ensp;运行主函数，调用 http://localhost:8080/add ,注意传值（推荐使用插件 RestService，非常方便），便可以看到相关的信息

```shell script
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─finance
│  │  │          └─order
│  │  │              └─demo
│  │  │                  ├─controller : rest 接口
│  │  │                  ├─kafka : 生产者、消费者相关实现
│  │  │                  └─model : order entity
```

### 作业思路
&ensp;&ensp;&ensp;&ensp;本题中的消息的有序、不丢失、仅处理一次是关键，重点就是考虑这两部分的配置和实现

#### 消息的有序性
&ensp;&ensp;&ensp;&ensp;由于在本题中使用的消息队列是kafka，有序性从两个方面保证：一是唯一生产者；而是kafka topic 的单分区

&ensp;&ensp;&ensp;&ensp;订单增加只能通过接口调用进行添加，接口调用后kafka消息发送接口进行发送，消息发送函数加锁，这样就已订单请求到达的先后为序

&ensp;&ensp;&ensp;&ensp;发送接口的性能需要考虑一下

&ensp;&ensp;&ensp;&ensp;消息的topic 设置为单分区多副本，即可保证消息在kafka队列中的有序性

#### 消息不丢失
&ensp;&ensp;&ensp;&ensp;首先思考下在哪些场景下消息会发生丢失，数据经历的场景如下：

```xml
producer --> kafka queue --> consumer
```

&ensp;&ensp;&ensp;&ensp;数据可能丢失的场景又三个：生产者与kafka的网络问题发生丢失、kafka本身故障导致消息丢失、消费者故障导致丢失

&ensp;&ensp;&ensp;&ensp;生产者可能由于网络原因，消息发送失败，如果忽略的话就会导致丢失，这个时候需要进行重试，在重试一定次数不成功以后，上报人工处理

&ensp;&ensp;&ensp;&ensp;kafka所在的机器如果出现故障，可能会导致数据丢失，这个时候需要使用多副本，保证消息不会丢失，而且生产者客户端在发送消息时需要配置确保所有副本写入成功

&ensp;&ensp;&ensp;&ensp;消费者可能因为程序故障导致消息丢失，借助kafka的offset基本能在故障后重新读取相应的数据进行处理（这里视读取数据，提交偏移量为一次完整的操作）

#### 消息仅处理一次
&ensp;&ensp;&ensp;&ensp;消息的重复可能发生在生产者端和消息这端

&ensp;&ensp;&ensp;&ensp;kafka本身有了通过id去重的机制，生产者端之间进行使用即可（API默认进行保证，不用设置）

&ensp;&ensp;&ensp;&ensp;消费者端通过偏移量也能保证消息只消费一次

### 有序性、可用性、不可重复性策略总结

- 有序性：
    - 生产者端：通过接口调用唯一的生产者端
    - kafka：设置单分区
- 可用性：
    - 生产者端：重试策略，失败上报人工；发送所有副本均ack成功
    - kafka：设置多副本
    - 消费者端：借助于偏移量机制，程序自行设计进行保障
- 不可重复性：
    - 生产者端、kafka：本身默认支持
    - 消费者端：借助偏移量机制，程序自行设计进行保障
    
## 程序相关实现关键代码
&ensp;&ensp;&ensp;&ensp;在上面的思路中，更多的程序设置在producer中，需要设置ack all和重试机制。kafka本身运维设置即可。consumer使用自动提交位移即可

### producer ACK All 和 重试 设置
&ensp;&ensp;&ensp;&ensp;在producer配置中需要加上下面的配置：

```java
class KafkaUtil {
    ......

    /**
     * 生成kafka生产者
     * 配置 ack all 确保消息
     * @param servers kafka address
     * @return kafka producer
     */
    static KafkaProducer<String, String> createProducer(String servers) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", servers);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 默认是 all，这里明确设置一下
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        // 如果设置 retry 次数大于0，需要设置这个为1
        properties.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);
        // 设置重试次数
        properties.put(ProducerConfig.RETRIES_CONFIG, 3);
        // 设置 timeout
        properties.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 15_000);
        // 设置 重试间隔为1秒
        properties.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 1_000);
        return new KafkaProducer<>(properties);
        return new KafkaProducer<>(properties);
    }
    ......
}
```

### 其他
&ensp;&ensp;&ensp;&ensp;代码还是相对比较简单，需要注意 producer send 是异步执行的，需要进行阻塞获取，保证同步，这样订单才能确保顺序写入

## 疑惑
&ensp;&ensp;&ensp;&ensp;有点疑惑的是设置的重试不知道生效了吗，能够失败，但感觉失败很快，不像重试发送的样子
    
## 参考链接
- [springboot项目中实现启动时开启一个异步线程去执行任务](https://blog.csdn.net/denghonghao/article/details/106389009)
- [producer 的各种高级特性设置：Kafka Tutorial 13: Creating Advanced Kafka Producers in Java](http://cloudurable.com/blog/kafka-tutorial-kafka-producer-advanced-java-examples/index.html)