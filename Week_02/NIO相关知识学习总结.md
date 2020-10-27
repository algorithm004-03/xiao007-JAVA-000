# NIO相关知识学习总结
***
*这里没有详细说明底层机制之类的，参考链接中前三个是相关参考，现在理解还不是太深，暂时这里写下NIO的应用优势*

## 为什么要学习NIO
&ensp;&ensp;&ensp;&ensp;虽然现在基本上各种库已经将I/O模式封装好了，不用自己去写这些底层代码，但了解其原理能帮助我们更好的编写出好代码和排查解决开发中遇到的问题

## NIO用于解决什么问题
&ensp;&ensp;&ensp;&ensp;用于提升网络通信效率。

&ensp;&ensp;&ensp;&ensp;通过下面单线程阻塞服务、多线程阻塞服务、线程池阻塞服务、Netty服务的接口性能测试中我们来感受下,代码工程在GitHub上：[server](https://github.com/lw1243925457/JAVA-000/tree/main/Week_02)

&ensp;&ensp;&ensp;&ensp;单线程阻塞服务

```java
package server.custom;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 单线程阻塞服务
 */
public class SingleServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                service(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void service(Socket socket) {
        try {
            Thread.sleep(20);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 ok");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println();
            printWriter.println("hello.nio");
            printWriter.close();
            socket.close();
            System.out.println(111);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

&ensp;&ensp;&ensp;&ensp;多线程版本

```java
package server.custom;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多线程阻塞服务
 */
public class MultiThreadServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                new Thread(() -> {
                    service(socket);
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void service(Socket socket) {
        try {
            Thread.sleep(20);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 ok");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println();
            printWriter.println("hello.nio");
            printWriter.close();
            socket.close();
            System.out.println(111);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

&ensp;&ensp;&ensp;&ensp;线程池版本

```java
package server.custom;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池服务
 */
public class ThreadPoolServer {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(40);
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                executorService.execute(() -> service(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void service(Socket socket) {
        try {
            Thread.sleep(20);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 ok");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println();
            printWriter.println("hello.nio");
            printWriter.close();
            socket.close();
            System.out.println(111);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

&ensp;&ensp;&ensp;&ensp;下面四个文件都是Netty版本的

```java
// Netty

package server.netty;


public class NettyServerApplication {

    public static void main(String[] args) {
        HttpServer server = new HttpServer(false,8808);
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
```

```java
package server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpServer {
    private static Logger logger = LoggerFactory.getLogger(HttpServer.class);

    private boolean ssl;
    private int port;

    public HttpServer(boolean ssl, int port) {
        this.port=port;
        this.ssl=ssl;
    }

    public void run() throws Exception {
        final SslContext sslCtx;
        if (ssl) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContext.newServerContext(ssc.certificate(), ssc.privateKey());
        } else {
            sslCtx = null;
        }

        EventLoopGroup bossGroup = new NioEventLoopGroup(3);
        EventLoopGroup workerGroup = new NioEventLoopGroup(1000);

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                    .option(EpollChannelOption.SO_REUSEPORT, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
                    //.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO)).childHandler(new HttpInitializer(sslCtx));

            Channel ch = b.bind(port).sync().channel();
            logger.info("开启netty http服务器，监听地址和端口为 " + (ssl ? "https" : "http") + "://127.0.0.1:" + port + '/');
            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
```

```java
package server.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;

public class HttpInitializer extends ChannelInitializer<SocketChannel> {
	private final SslContext sslCtx;

	public HttpInitializer(SslContext sslCtx) {
		this.sslCtx = sslCtx;
	}

	@Override
	public void initChannel(SocketChannel ch) {
		ChannelPipeline p = ch.pipeline();
		if (sslCtx != null) {
			p.addLast(sslCtx.newHandler(ch.alloc()));
		}
		p.addLast(new HttpServerCodec());
		//p.addLast(new HttpServerExpectContinueHandler());
		p.addLast(new HttpObjectAggregator(1024 * 1024));
		p.addLast(new HttpHandler());
	}
}
```

```java
package server.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(HttpHandler.class);

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            //logger.info("channelRead流量接口请求开始，时间为{}", startTime);
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            String uri = fullRequest.uri();
            //logger.info("接收到的请求url为{}", uri);
            if (uri.contains("/test")) {
                handlerTest(fullRequest, ctx);
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    private void handlerTest(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        FullHttpResponse response = null;
        try {
            String value = "hello,kimmking";
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());

        } catch (Exception e) {
            logger.error("处理测试接口出错", e);
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
```

&ensp;&ensp;&ensp;&ensp;下面我们使用性能测试工具：[superbenchmarker](https://github.com/aliostad/SuperBenchmarker)，进行测试,测试的结果如下：

- 简朴HTTP server：32.3
- 多线程版本：1091.7
- 线程池版本：1163
- netty：5938.6

&ensp;&ensp;&ensp;&ensp;从上面的数据中我们可以看到Netty或者NIO编程的性能提升有多明显。

&ensp;&ensp;&ensp;&ensp;下面我们来了解下网络模型的相关概念：

### 三种经典的I/O模式
&ensp;&ensp;&ensp;&ensp;有三种经典的I/O模型：BIO（阻塞I/O）,上面例子中的单线程、多线程、线程池都属于BIO模型；NIO（非阻塞IO），上面例子的中Netty是NIO模式；AIO（异步I/O），由于某些原因，虽然性能理论上要比NIO好，但还没有很好的应用。

&ensp;&ensp;&ensp;&ensp;下面是吃饭场景对比I/O模式

- BIO（阻塞I/O） -- 食堂排队打饭模式：排队在窗口，打好才走
- NIO（非阻塞I/O） -- 点单、等待被叫模式：等待被叫，好了自己去端
- AIO（异步I/O） -- 包厢模式：点单后菜直接被端上桌

### 阻塞与非阻塞、同步与异步
&ensp;&ensp;&ensp;&ensp;阻塞和非阻塞是对于通信中的数据而言的：如果没有数据会一直等下去那就是阻塞；不等就是非阻塞

&ensp;&ensp;&ensp;&ensp;同步和异步是对于怎样取数据而言的：如果数据好了，程序自己去取，那就是同步；如果是数据就绪后回调给程序就是异步

&ensp;&ensp;&ensp;&ensp;对应吃饭的场景是：

- 阻塞与非阻塞：对于菜而言，阻塞就是一直等菜，反之
- 同步与异步：对应菜好了怎么取，自己去取就是同步，等服务员端上来就是异步

### 为什么删掉已经做好的AIO支持：
- Windows实现成熟，但很少做服务器
- Linux的AIO不够成熟
- Linux下AIO相比较NIO性能提升不明显

## 参考链接
- [20 | 大名⿍⿍的select：看我如何同时感知多个I/O事件](https://time.geekbang.org/column/article/138948)
- [21 | poll：另一种I/O多路复用](https://time.geekbang.org/column/article/140520)
- [22 | 非阻塞I/O：提升性能的加速器](https://time.geekbang.org/column/article/141573)
- [08 | Netty怎么切换三种I/O模式？](https://time.geekbang.org/course/detail/100036701-147214)