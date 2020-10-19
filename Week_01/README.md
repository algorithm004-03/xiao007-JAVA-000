# 学习笔记
***
*这里将作业写到本周课程的工程的：作业.md文件中，这里写学习记录*

*相关的代码放到当前目录下的：example工程中*

## 作业说明
&ensp;&ensp;&ensp;&ensp;作业工程是当前目录下的code

&ensp;&ensp;&ensp;&ensp;作业解答文件是：[作业解答:code/README.md](./作业.md)

## 一.Java语义概览（需要掌握程度：了解）
### 知识概览
&ensp;&ensp;&ensp;&ensp;Java是一种面向对象、静态类型、编译运行，有VM/GC和运行时的、跨平台的高级语言

&ensp;&ensp;&ensp;&ensp;和一些看到的专栏描述有点冲突，专栏《Java核心面试知识点》关于Java是解释执行还是编译执行有不同的解读：

&ensp;&ensp;&ensp;&ensp;代码首先编译成字节码，jvm在编译成机器码后执行。但动态编译器（JIT）会将热点代码编译成机器码，这种属于编译执行。则Java有解释执行，也有编译执行。

## 二.字节码（需要掌握程度：了解）
### 知识概览
&ensp;&ensp;&ensp;&ensp;这个知识点好像在工作中用的不多，但了解这些知识点，在未来如果在底层方面遇到问题，起码有方向和思路。

- 四种指令类型
- 基于栈的计算机器
- 方法调用的指令

### 相关扩展
&ensp;&ensp;&ensp;&ensp;下面是助教提到的一些应用：

- 现有的开源应用有asm,cglib, aop的代理，  监控的agent实现，都需要动态修改字节码

### 自己一些思路和尝试
#### int、Integer在字节码中是怎样的体现？静态和final呢？

## 3.类加载器（需要掌握程度：熟练）
### 知识概览
&ensp;&ensp;&ensp;&ensp;该部分知识在工作中有实际应用，掌握其原理，助于在工作中正确使用

- 类的7个生命周期（步骤）：加载、链接（效验、准备、解析）、初始化、使用、卸载
- 类的8个加载时机：main、new、遭到调用静态方法的类、遭到访问静态字段的类、子类触发父类、default、发射、MethodHandle
- 不会触发类初始化：6种
- 三类加载器：启动类加载器、扩展类加载器、应用类加载器
- 加载器特点：双亲委托、负责依赖、缓存加载
- 显示当前Classloader加载了哪些Jar
- 自定义Classloader
- 添加引用类的几种方式

### 自己的一些思考和尝试
#### 加载重名冲突包

#### 类加载的一些技巧

## 4.Java内存模型（需要掌握程度：精通）
### 知识概览
&ensp;&ensp;&ensp;&ensp;这部分知识应该是基础，涉及到后面的GC，多线程编程、JVM调优等重要知识点

- JVM内存整体结构
    - 进程
    - 栈、堆、非堆、JVM自身
        - 堆：
            - 年轻代
                - 新生代
                - S0
                - S1
            - 老年代
        - 非堆
            - 元数据区：常量池方法区
    - 线程栈
    - 帧

- CPU与内存行为
    - CPU乱序执行
    - volatile关键字
    - 原子性操作
    - 内存屏障

## 5.JVM启动参数（掌握程度：熟练）
&ensp;&ensp;&ensp;&ensp;介绍参数相关约定，这块也相对重要，JVM调优之类的需要

- -：标准参数
- -D：设置系统属性
- -X：非标准参数
- -XX：非稳定参数，控制JVM行为
- -XX：+-Flags：+-是对布尔值进行开关
- -XX：key=value 指定某个选项的值

- JVM启动参数
    - 1.系统属性参数
    - 2.运行模式参数:server/client/Xint/Xcomp/Xmixed
    - 3.堆内存设置参数:Xmx/Xms/Xmn/Xx/XX/Xss
    - 4.GC设置参数:-XX:+UseG1GC/-XX:+UseConcMarkSweepGC/-XX:+UseSerialGC/-XX:+UseParallelGC/-XX:+UnlockExperimentalVMOptions -XX:+UseZGC/-XX:+UnlockExperimentalVMOptions -XX:+UseShenandoahGC
    - 5.分析诊断参数
    - 6.JavaAgent参数

### 自己的思考和尝试
#### 各个JVM版本的默认GC

## 6.JDK命令行工具
- 常用命令行工具：jps,jinfo,jstat,jmap,jstack,jcmd,jrunscript,jjs

64位*线程数*系数13 / 10

大部分都是可以本地和远程的

java 常用命令行整理

### 命令详解与尝试
*一些小提示：使用命令的时候，直接一个 -help 就能对命令用途和用法有个大概的了解*

#### [jps](https://docs.oracle.com/en/java/javase/13/docs/specs/man/jps.html)
命令用途：查看当前系统中的Java进程列表

常用的命令有下面两种：

```bash
# 查看当前系统中Java进程，显示进程号和名称
jps

# 查看当前系统中Java进程，显示进程号和名称，还有更相信的启动信息
jps -lvm
```

#### [jinfo](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jinfo.html)
命令用途：查看指定Java进程的详细信息。能看到当前系统的信息，如显示win10；java路径；运行程序的用户；Java版本信息；VM信息

常用命令示例：

```bash
jinfo <pid>
```

#### [jstat](https://docs.oracle.com/javase/7/docs/technotes/tools/share/jstack.html)
命令用途：打印Java线程中的信息，类名，线程名（线程尽量要有一个标识名称，便于调试查看）。常用语查看当前进程是否有死锁

下面是一个死锁的程序示例代码：

```java
public class DeadLockSample extends Thread {
    private String first;
    private String second;

    public DeadLockSample(String name, String first, String second) {
        super(name);
        this.first = first;
        this.second = second;
    }

    @Override
    public void run() {
        synchronized (first) {
            System.out.println(this.getName() + " get lock: " + first);
            try {
                Thread.sleep(1000);
                synchronized (second) {
                    System.out.println(this.getName() + " get lock: " + second);
                }
            } catch (InterruptedException e) {

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String lockA = "LockA";
        String lockB = "LockB";

        DeadLockSample t1 = new DeadLockSample("Thread1", lockA, lockB);
        DeadLockSample t2 = new DeadLockSample("Thread2", lockB, lockA);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
```

使用就stack查看进程就可以发现在最后打印了死锁信息

```bash
Found one Java-level deadlock:
=============================
"Thread1":
  waiting to lock monitor 0x000002a2e47ecb80 (object 0x0000000621291fe0, a java.lang.String),
  which is held by "Thread2"
"Thread2":
  waiting to lock monitor 0x000002a2e47ee980 (object 0x0000000621291fb0, a java.lang.String),
  which is held by "Thread1"

Java stack information for the threads listed above:
===================================================
"Thread1":
        at com.company.DeadLockSample.run(DeadLockSample.java:20)
        - waiting to lock <0x0000000621291fe0> (a java.lang.String)
        - locked <0x0000000621291fb0> (a java.lang.String)
"Thread2":
        at com.company.DeadLockSample.run(DeadLockSample.java:20)
        - waiting to lock <0x0000000621291fb0> (a java.lang.String)
        - locked <0x0000000621291fe0> (a java.lang.String)

Found 1 deadlock.
```

使用示例：

```
jstack pid
```

#### jstat

#### jmap

#### jhat

#### jcmd


## 参考资料记录
- Java训练营课程及相关课件
- [第23讲 | 请介绍类加载过程，什么是双亲委派模型？](https://time.geekbang.org/column/article/9946)
- [Class Loaders in Java](https://www.baeldung.com/java-classloaders)
- [Java8 JVM 参数官方说明文档](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/java.html)