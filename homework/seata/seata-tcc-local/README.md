# Seata TCC 示例
***
## 前言
&ensp;&ensp;&ensp;&ensp;代码还是容易看懂的，但还是要大致了解框架的大致原理，下面是一点想法，如果你也看了示例，但还是不太确定怎么用的，可以参考下：

- [分布式事务TCC](https://github.com/lw1243925457/SE-Notes/blob/master/profession/program/java/%E5%88%86%E5%B8%83%E5%BC%8F%E4%BA%8B%E5%8A%A1/%E5%88%86%E5%B8%83%E5%BC%8F%E4%BA%8B%E5%8A%A1TCC%E5%A4%A7%E8%87%B4%E5%8E%9F%E7%90%86.md)

&ensp;&ensp;&ensp;&ensp;整个项目也是参考的Seata官方的示例的，也可以去看下[官网的原理说明](http://seata.io/zh-cn/docs/overview/what-is-seata.html)和[官方的TCC Local Example](https://github.com/seata/seata-samples/tree/master/tcc/local-tcc-sample)

## 程序说明
&ensp;&ensp;&ensp;&ensp;这里模拟了两个服务：用户和商家，用户账户操作和商家库存操作作为一个事务

&ensp;&ensp;&ensp;&ensp;在StoreServiceImpl、UserServiceImpl，应该是要放数据库之类的操作，比如更新账户余额、还原回滚余额之类的。但不放也达到目的，自己应该是大致明白这个TCC
了，所有就没有放了。

### 运行说明
- SeataServer：首先运行这个，把Seata的服务跑起来，这个应该是TC中心
- TccLocalDemo：程序运行示例，模拟事务运行成功和失败回滚