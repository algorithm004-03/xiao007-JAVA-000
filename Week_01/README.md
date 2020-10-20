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

#### [jstat](https://docs.oracle.com/en/java/javase/14/docs/specs/man/jstat.html)
 命令用途：用于查看虚拟机中的性能统计信息，非常有用，

 使用示例：

 ```bash
 jstat -gc pid 100 100
 jstat -class pid 100 100
 ```

#### [jmap](https://docs.oracle.com/javase/7/docs/technotes/tools/share/jmap.html)
命令用途：查看堆信息

使用示例：

```bash
jmap -histo pid
```

#### 【jcmd】(https://docs.oracle.com/en/java/javase/13/docs/specs/man/jcmd.html)
命令用途：这个命令有点类似基础命令，上面那些的命令可以看做在这个命令之上的封装使用

## 7.JDK内置图形化工具

- jconsole:显示线程、内存、CPU等等
- jvisualvm：
- VisualGC：IDEA插件
- jmc

## 8.GC的背景与一般原理
Java是自动内存管理，在程序中无法人工管理，在生成新对象时需要消耗内存，如果不对无用的对象进行回收，就会导致内存占满，无法为新对象分配内存，程序崩溃。

什么是无用对象：利用可达性分析，不可达的对象就是无用对象，可进行回收

可以作为GC Roots的对象标准：
- 当前正在执行的方法里的局部变量和输入参数
- 活动线程
- 所有类的静态字段
- JNI引用

分代假设：分为年轻代代和老年代，年轻代中又分新生代、S0、S1
- 新对象生成时在新生代中；大对象之间放到老年代中
- 新生代用满了，触发年轻代 Minor GC，将新生代和S0中存活的对象放到S1中，并标记其存活次数，最后S0和S1互换
- 当存活次数达到要求（这个可以进行设置：-XX:+MaxTenuringThreshold），则这个对象就会晋升到老年代
- 当单个S去被占用50%（这个可以进行设置：-XX:TargetSurvivorRatio），较高存活次数的对象也晋升到老年代

Minor GC中需要注意的点：
- 老年代中引用了新生代中的对象：记录被老年代中引用的新生代中的对象，并将其作为GC Roots，复制后进行引用更新，技术名叫：卡表
- 为啥使用复制，而不是移动：大部分对象已经死亡，复制的数据少

回收算法：
- 标记-清除
- 标记-复制
- 标记-清除-整理

并行GC和CMS可以处理循环依赖，只扫描部分对象（只需扫描可达的对象），这部分可以并行，不影响业务运行，但在清楚和压缩阶段，必须停止业务代码（STW）

## 9.串行GC/并行GC（Serial GC / Parallel GC)
### 串行 GC（Serial GC）/ParNewGC
配置：
- -XX:+UseSerialGC 配置串行 GC
- -XX:+USeParNewGC 配置ParNewGC

年轻代使用标记-复制算法，老年代使用标记-清除-整理算法

都是单线程，不能并行进行梳理，进行垃圾回收时触发STW，老式电脑容易卡死

适合几百MB堆内存的JVM，单核CPU：几百MB本身较小，回收也快，在单核中时间切片就只能一个线程运行

### 并行 GC（Parallel GC）
配置：
-XX：+UseParallelGC
-XX：+UseParallelOldGC
-XX：+UseParallelGC -XX:+UseParallelOldGC
-XX：ParallelGCThreads=N 来指定 GC 线程数， 其默认值为 CPU 核心数。

年轻代使用标记-复制算法，老年代使用标记-清除-整理算法

多线程，年轻代和老年代GC都会触发STW，只是串行GC的改进，利用GC多线程，加快GC速度

## 10.CMS GC / G1 GC
### CMS GC
配置：
- -XX：+UseConcMarkSweepGC

年轻代使用并行STW标记-复制，老年代使用并发标记-清除

设计目标是避免在老年代GC出现长时间的卡顿，主要通过下面两种手段达成：
- 1.不对老年代进行整理，使用空闲列表来管理内存空间的回收
- 2.在标记-清除阶段的大部分工作和应用线程一起并发执行

CMS GC六阶段
- 1. Initial Mark（初始标记）：STW，标记GC Roots
- 2.Concurrent Mark（并发标记）：并行标记
- 3. Concurrent Preclean(并发预清理)：卡片标记
- 4.阶段 4: Final Remark（最终标记）：STW，最终标记存活对象
- 5: Concurrent Sweep（并发清除）：删除回收对象
- 6.Concurrent Reset（并发重置）：重置算法内部数据

CMS优缺点：
- 优点：利用GC分阶段并行降低了GC STW时间
- 缺点：老年代内存碎片问题，堆内存较大会造成不可预测的STW

### G1 GC
配置：
- -XX:+UseG1GC
- -XX:MaxGCPauseMillis=50
- -XX:G1NewSizePercent：初始年轻代占整个Java Heap的大小，默认值为5%
- -XX:G1MaxNewSizePercent：最大年轻代占整个Java Heap的大小，默认值为60%；
- -XX:G1HeapRegionSize：设置每个Region的大小，单位MB，需要为1，2，4，8，16，32中的某个值，默认是 堆内存的1/2000。如果这个值设置比较大，那么大对象就可以进入Region了
- -XX:ConcGCThreads：与Java应用一起执行的GC线程数量，默认是Java线程的1/4，减少这个参数的数值可能会 提升并行回收的效率，提高系统内部吞吐量。如果这个数值过低，参与回收垃圾的线程不足，也会导致并行回收机制耗时加长
- -XX:+InitiatingHeapOccupancyPercent（简称IHOP）：G1内部并行回收循环启动的阈值，默认为Java Heap的45%。这个可以理解为老年代使用大于等于45%的时候，JVM会启动垃圾回收。这个值非常重要，它决定了在什么 时间启动老年代的并行回收
- -XX:G1HeapWastePercent：G1停止回收的最小内存大小，默认是堆大小的5%。GC会收集所有的Region中的对 象，但是如果下降到了5%，就会停下来不再收集了。就是说，不必每次回收就把所有的垃圾都处理完，可以遗留 少量的下次处理，这样也降低了单次消耗的时间。
- -XX:G1MixedGCCountTarget：设置并行循环之后需要有多少个混合GC启动，默认值是8个。老年代Regions的回 收时间通常比年轻代的收集时间要长一些。所以如果混合收集器比较多，可以允许G1延长老年代的收集时间。
- -XX:+G1PrintRegionLivenessInfo：这个参数需要和 -XX:+UnlockDiagnosticVMOptions 配合启动，打印JVM的调试信息，每个Region里的对象存活信息
- -XX:G1ReservePercent：G1为了保留一些空间用于年代之间的提升，默认值是堆空间的10%。因为大量执行回收的地方在年轻代（存活时间较短），所以如果你的应用里面有比较大的堆内存空间、比较多的大对象存活，这里需要保留一些内存。
- -XX:+G1SummarizeRSetStats：这也是一个VM的调试信息。如果启用，会在VM退出的时候打印出RSets的详细总结信息。如果启用-XX:G1SummaryRSetStatsPeriod参数，就会阶段性地打印RSets信息
- -XX:+GCTimeRatio：这个参数就是计算花在Java应用线程上和花在GC线程上的时间比率，默认是9，跟新生代内存的分配比例一致。这个参数主要的目的是让用户可以控制花在应用上的时间，G1的计算公式是100/（1+GCTimeRatio）。这样如果参数设置为9，则最多10%的时间会花在GC工作上面。Parallel GC的默认值是99，表示1%的时间被用在GC上面，这是因为Parallel GC贯穿整个GC，而G1则根据Region来进行划分，不需要全局性扫描整个内存堆。
- -XX:+UseStringDeduplication：手动开启Java String对象的去重工作，这个是JDK8u20版本之后新增的参数，主要用于相同String避免重复申请内存，节约Region的使用。
- -XX:MaxGCPauseMills：预期G1每次执行GC操作的暂停时间，单位是毫秒，默认值是200毫秒，G1会尽量保证控制在这个范围内

G1的全称是Garbage-First，意为垃圾优先，哪一块的垃圾最多就优先清理它。

设计目标：将STW时间和分布，变成可预期且可配置的

G1 GC 处理步骤
- 1.年轻代模式转移暂停：年轻代空间满了以后，回收好的存活对象拷贝到存活区
- 2.并发标记：过程与CMS基本一样，构建每个堆块的存活状态，用于后面执行老年代区域的垃圾收集；当堆内存总体使用比例达到一定数值，就会触发并发标记（默认45%，设置参数：InitiatingHeapOccupancyPercent）
    - 阶段1：Initial Mark(初始标记)：标记所有从GC根对象直接可达的对象
    - 阶段 2: Root Region Scan(Root区扫描)：标记所有从“根区域”可达的存活对象；根区域：非空的区域，以及在标记过重不得不收集的区域
    - 阶段 3: Concurrent Mark(并发标记)：与CMS并发标记类似，遍历对象图，在一个特殊的位图中标记能访问到的对象
    - 阶段 4: Remark(再次标记)：SWT，标记所有在并发标记开始是未被标记的存活对象
    - 阶段 5: Cleanup(清理)：也需要短暂的STW，统计小堆块中所有存活的对象，并将小堆块进行排序，以提升GC效率，维护并发标记的内部状态；不含存活对象的小堆块直接回收
- 3.转移暂停（混合模式）：并发标记完成后，G1执行一次混合收集，包含年轻代和老年代

G1 GC注意事项
特别需要注意的是，某些情况下G1触发了Full GC，这时G1会退化使用Serial收集器来完成垃圾的清理工作，它仅仅使用单线程来完成GC工作，GC暂停时间将达到秒级别的。
- 1.并发模式失败：G1启动标记周期，但在Mix GC之前，老年代就被填满，这时候G1会放弃标记周期。
- 解决办法：增加堆大小，或者 调整周期（例如增加线程数-XX:ConcGCThreads等）

- 2.晋升失败：没有足够的内存供存活对象或晋升对象使用，由此触发了Full GC(to-space exhausted/to-space overflow）。 
- 解决办法：
    - a)增加 -XX:G1ReservePercent 选项的值（并相应增加总的堆大小）增加预留内存量。
    - b)通过减少 -XX:InitiatingHeapOccupancyPercent 提前启动标记周期。
    - c)也可以通过增加 -XX:ConcGCThreads 选项的值来增加并行标记线程的数目。

- 3.巨型对象分配失败:当巨型对象找不到合适的空间进行分配时，就会启动Full GC，来释放空间。
- 解决办法：增加内存或者增大-XX:G1HeapRegionSize

## 11.ZGC / Shenandoah GC
### ZGC
配置
- -XX:+UnlockExperimentalVMOptions 
- -XX:+UseZGC 
- -Xmx16g

ZGC最主要的特点包括:
- 1. GC 最大停顿时间不超过 10ms
- 2. 堆内存支持范围广，小至几百 MB 的堆空间，大至4TB 的超大堆内存（JDK13升至16TB）
- 3. 与 G1 相比，应用吞吐量下降不超过15%
- 4. 当前只支持 Linux/x64 位平台，JDK15后支持MacOS和Windows系统

### ShennandoahGC
配置：
- -XX:+UnlockExperimentalVMOptions 
- -XX:+UseShenandoahGC 
- -Xmx16g

Shenandoah GC立项比ZGC更早，设计为GC线程与应用线程并发执行的方式，通过实现垃圾回收过程的并发处理，改善停顿时间，使得GC执行线程能够在业务处理线程运行过程中进行堆压缩、标记和整理，从而消除了绝大部分的暂停时间。
Shenandoah 团队对外宣称ShenandoahGC的暂停时间与堆大小无关，无论是200MB 还是 200 GB的堆内存，都可以保障具有很低的暂停时间（注意:并不像ZGC那样保证暂停时间在10ms以内）。

## GC 如何选择
选择正确的GC算法，唯一可行的方式就是去尝试，一般性的指导原则：
- 1. 如果系统考虑吞吐优先，CPU资源都用来最大程度处理业务，用Parallel GC；
- 2. 如果系统考虑低延迟有限，每次GC时间尽量短，用CMS GC；
- 3. 如果系统内存堆较大，同时希望整体来看平均GC时间可控，使用G1 GC。

对于内存大小的考量：
- 1. 一般4G以上，算是比较大，用G1的性价比较高。
- 2. 一般超过8G，比如16G-64G内存，非常推荐使用G1 GC。

## GC总结
到目前为止，我们一共了解了Java目前支持的所有GC算法，一共有7类:
- 1. 串行GC（Serial GC）: 单线程执行，应用需要暂停；
- 2. 并行GC（ParNew、Parallel Scavenge、Parallel Old）: 多线程并行地执行垃圾回收，关注与高吞吐；
- 3. CMS（Concurrent Mark-Sweep）: 多线程并发标记和清除，关注与降低延迟；
- 4. G1（G First）: 通过划分多个内存区域做增量整理和回收，进一步降低延迟；
- 5. ZGC（Z Garbage Collector）: 通过着色指针和读屏障，实现几乎全部的并发执行，几毫秒级别的延迟，线性可扩展；
- 6. Epsilon: 实验性的GC，供性能分析使用；
- 7. Shenandoah: G1的改进版本，跟ZGC类似。

可以看出GC算法和实现的演进路线:
1. 串行 -> 并行: 重复利用多核CPU的优势，大幅降低GC暂停时间，提升吞吐量。
2. 并行 -> 并发: 不只开多个GC线程并行回收，还将GC操作拆分为多个步骤，让很多繁重的任务和应用线程 一起并发执行，减少了单次GC暂停持续的时间，这能有效降低业务系统的延迟。
3. CMS -> G1: G1可以说是在CMS基础上进行迭代和优化开发出来的，划分为多个小堆块进行增量回收，这 样就更进一步地降低了单次GC暂停的时间
4. G1 -> ZGC: ZGC号称无停顿垃圾收集器，这又是一次极大的改进。ZGC和G1有一些相似的地方，但是底层 的算法和思想又有了全新的突破。

## 参考资料记录
- Java训练营课程及相关课件
- [第23讲 | 请介绍类加载过程，什么是双亲委派模型？](https://time.geekbang.org/column/article/9946)
- [Class Loaders in Java](https://www.baeldung.com/java-classloaders)
- [Java8 JVM 参数官方说明文档](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/java.html)
- [class反编译：JD-GUI 1.6.6](https://github.com/java-decompiler/jd-gui/releases/tag/v1.6.6)