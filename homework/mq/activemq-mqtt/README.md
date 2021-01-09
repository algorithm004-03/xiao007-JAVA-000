# Activemq MQTT 简单消息推送示例
***
## 简介
&ensp;&ensp;&ensp;&ensp;简单使用 MQTT 连接 Activemq 进行消息推送的示例代码

## 编写详情
### 环境准备
&ensp;&ensp;&ensp;&ensp;使用docker启动Activemq，查看MQTT协议监听端口是否正确，如下命令，显示1883：

```shell script
docker run -dit --name activemq -p 11616:61616 -p 8161:8161 -p 1883:1883 rmohr/activemq
docker exec -ti activemq cat /opt/activemq/conf/activemq.xml
```

### 订阅者
&ensp;&ensp;&ensp;&ensp;类似手机客户端，接收消息推送，简单打印收到的消息，代码如下：

```java
import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.*;

public class Listener {

    public static void main(String[] args) throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost("localhost", 1883);
        mqtt.setUserName("admin");
        mqtt.setPassword("admin");

        final CallbackConnection connection = mqtt.callbackConnection();
        connection.listener(new org.fusesource.mqtt.client.Listener() {

            @Override
            public void onConnected() {

            }

            @Override
            public void onDisconnected() {

            }

            @Override
            public void onPublish(UTF8Buffer utf8Buffer, Buffer buffer, Runnable runnable) {
                String message = buffer.utf8().toString();
                System.out.println("Receive message : " + message);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

        connection.connect(new Callback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Topic[] topics = {new Topic("mqttTest", QoS.AT_LEAST_ONCE)};
                connection.subscribe(topics, new Callback<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        System.out.println("subscribe success");
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("subscribe failed");
                    }
                });
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });

        synchronized (Listener.class) {
            while (true) {
                Listener.class.wait();
            }
        }
    }
}
```

### 发布者
&ensp;&ensp;&ensp;&ensp;进行消息的发布，代码大致如下：

```java
import org.fusesource.hawtbuf.AsciiBuffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.FutureConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;

public class Publisher {

    public static void main(String[] args) throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost("localhost", 1883);
        mqtt.setUserName("admin");
        mqtt.setPassword("admin");

        FutureConnection connection = mqtt.futureConnection();
        connection.connect().await();
        System.out.println("connect");

        int messageAmount = 10;
        UTF8Buffer topic = new UTF8Buffer("mqttTest");
        while (messageAmount > 0) {
            connection.publish(topic, new AsciiBuffer("test message" + messageAmount), QoS.AT_LEAST_ONCE, false);
            System.out.println("send message " + messageAmount);
            messageAmount -= 1;
        }

        connection.disconnect().await();
        System.out.println("disconnect");
    }
}
```

### 运行
&ensp;&ensp;&ensp;&ensp;先启动订阅者，再启动发布者，可以看到消息发送和接收

## 参考链接
- [ActiveMq之MQTT（即时聊天&物联网技术）](https://blog.hyaroma.com/articles/2018/03/22/1521722801548.html)
- [MQTT](https://activemq.apache.org/mqtt)

- activemq example:
    - [Listener.java](https://gemfury.com/spscommerce/rpm:activemq/-/content/opt/activemq/examples/mqtt/java/src/main/java/example/Listener.java)
    - [Publisher.java](https://gemfury.com/spscommerce/rpm:activemq/-/content/opt/activemq/examples/mqtt/java/src/main/java/example/Publisher.java)
    - [pom.xml](https://gemfury.com/spscommerce/rpm:activemq/-/content/opt/activemq/examples/mqtt/java/pom.xml)