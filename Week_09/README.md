# 第九周学习笔记
***
## 作业
### 一、改造自定义RPC的程序，提交到github

- [x] 1）尝试将服务端写死查找接口实现类变成泛型和反射
- [x] 2）尝试将客户端动态代理改成AOP，添加异常处理
- [x] 3）尝试使用Netty+HTTP作为client端传输方式
- [ ] 4)（选做☆☆）升级自定义RPC的程序：
- [ ] 1）尝试使用压测并分析优化RPC性能
- [x] 2）尝试使用Netty+TCP作为两端传输方式
- [ ] 3）尝试自定义二进制序列化
- [ ] 4）尝试压测改进后的RPC并分析优化，有问题欢迎群里讨论
- [ ] 5）尝试将fastjson改成xstream
- [x] 6）尝试使用字节码生成方式代替服务端反射

*作业详情及代码地址：[Rpcfx](https://github.com/lw1243925457/JAVA-000/tree/main/homework/rpc/rpc-demo)*

*后序更多的关于RPC框架的选做放到了一个单独的工程,RPC Dome框架项目地址：[Java-Rpc-Demo](https://github.com/lw1243925457/Java-Rpc-Demo)*

### 二、结合dubbo+hmily，实现一个TCC外汇交易处理，代码提交到github：

- 1）用户A的美元账户和人民币账户都在A库，使用1美元兑换7人民币；
- 2）用户B的美元账户和人民币账户都在B库，使用7人民币兑换1美元；
- 3）设计账户表，冻结资产表，实现上述两个本地事务的分布式事务

*作业详情及代码地址：[himly-tcc-dubbo](https://github.com/lw1243925457/JAVA-000/tree/main/homework/himly-tcc-dubbo)*

## 参考链接
- [https://code.google.com/archive/p/rpcfx/source](https://code.google.com/archive/p/rpcfx/source)