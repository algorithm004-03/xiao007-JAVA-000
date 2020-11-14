# 作业
***
## 作业1
### 作业要求
&ensp;&ensp;&ensp;&ensp;使Java里的动态代理，实现一个简单的AOP

### 实现说明
- 实现代码：https://github.com/lw1243925457/JAVA-000/tree/main/Week_05/code/src/main/java/com/example/code/class9/dynamicproxy
- 测试代码：https://github.com/lw1243925457/JAVA-000/blob/main/Week_05/code/src/test/java/com/example/code/class9/DynamicProxyTest.java

## 作业2
### 作业要求
&ensp;&ensp;&ensp;&ensp;写代码实现Spring Bean的装配，方式越多越好（XML、Annotation都可以）,提交到Github

### 实现说明
&ensp;&ensp;&ensp;&ensp;一共三种方式：

- 1.自动注解方式：
  - 实现代码：https://github.com/lw1243925457/JAVA-000/tree/main/Week_05/code/src/main/java/com/example/code/class9/auto
  - 测试代码：https://github.com/lw1243925457/JAVA-000/blob/main/Week_05/code/src/test/java/com/example/code/class9/AutoWiringExampleTest.java

- 2.Java代码方式：
  - 实现代码：https://github.com/lw1243925457/JAVA-000/tree/main/Week_05/code/src/main/java/com/example/code/class9/javacode
  - 测试代码：https://github.com/lw1243925457/JAVA-000/blob/main/Week_05/code/src/test/java/com/example/code/class9/JavaCodeExampleTest.java

- 2.Xml配置方式：
  - 实现代码：https://github.com/lw1243925457/JAVA-000/tree/main/Week_05/code/src/main/java/com/example/code/class9/xml
  - 测试代码：https://github.com/lw1243925457/JAVA-000/blob/main/Week_05/code/src/test/java/com/example/code/class9/XmlExampleTest.java

## 作业3
### 作业要求
&ensp;&ensp;&ensp;&ensp;实现一个 Spring XML 自定义配置，配置一组 Bean，例如：Student/Klass/School

### 实现说明
- 实现代码：https://github.com/lw1243925457/JAVA-000/tree/main/Week_05/code/src/main/java/com/example/code/class9/school
- 测试代码：https://github.com/lw1243925457/JAVA-000/blob/main/Week_05/code/src/test/java/com/example/code/class9/SchoolTest.java 

## 作业4
### 作业要求
&ensp;&ensp;&ensp;&ensp;给前面课程提供的 Student/Klass/School 实现自动配置和 Starter

### 实现说明
&ensp;&ensp;&ensp;&ensp;在本周项目项目工程文件夹：schoolStart 中实现了自动配置，使用 maven install打包。然后放到了另外一个工程：code中，在pom中引入，最后测试通过

- 实现代码：https://github.com/lw1243925457/JAVA-000/tree/main/Week_05/schoolStart
- 测试代码：https://github.com/lw1243925457/JAVA-000/blob/main/Week_05/code/src/test/java/com/example/code/class10/MySchoolAutoStarterlTest.java 

## 作业5
### 作业要求
&ensp;&ensp;&ensp;&ensp;研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：

- 1）使用 JDBC 原生接口，实现数据库的增删改查操作。
- 2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
- 3）配置 Hikari 连接池，改进上述操作。提交代码到 Github。

### 实现说明

- 实现代码：https://github.com/lw1243925457/JAVA-000/tree/main/Week_05/code/src/main/java/com/example/code/database/
  - jdbc：放置原生jdbc操作和事务操作
  - Hikari：放置Hikari简单示例
- 测试代码：
  - jdbc:运行main函数即可
  - Hikari 运行其中的：HikariApplication，设置为启动以后自动连接查询

## 作业6
### 作业要求
&ensp;&ensp;&ensp;&ensp;总结一下，单例的各种写法，比较它们的优劣

### 实现说明
- [Java单例总结](https://github.com/lw1243925457/SE-Notes/blob/master/profession/program/java/Java%E5%8D%95%E4%BE%8B%E6%A8%A1%E5%BC%8F.md)

## 作业7
### 作业要求
&ensp;&ensp;&ensp;&ensp;maven/spring 的 profile 机制，都有什么用法？

### 实现说明
#### 使用场景
&ensp;&ensp;&ensp;&ensp;maven/spring 的 profile 机制，都是用于不同环境的特定配置切换的。比如项目再测试环境、开发环境、生产环境，有各自不同的数据库，生产环境不需要测试环境的一些测试依赖之类的。如果每次在不同环境运行都要去改配置文件，那就会很麻烦，而且有时忘记改了，还会出问题。这个机制大致就是为每个环境配置好各自的配置文件，相应的修改也修改相应的配置，这样切换的方便，不容易出问题。

#### Maven profile的用法
&ensp;&ensp;&ensp;&ensp;配置方式大致如下

```java
<project>
   <profiles>
       <profile>
           <build>
               <defaultGoal>...</defaultGoal>
               <finalName>...</finalName>
               <resources>...</resources>
               <testResources>...</testResources>
               <plugins>...</plugins>
           </build>
           <reporting>...</reporting>
           <modules>...</modules>
           <dependencies>...</dependencies>
           <dependencyManagement>...</dependencyManagement>
           <distributionManagement>...</distributionManagement>
           <repositories>...</repositories>
           <pluginRepositories>...</pluginRepositories>
           <properties>...</properties>
       </profile>
   </profiles>
</project>  
```

&ensp;&ensp;&ensp;&ensp;使用 -p 参数来激活一个profile

```bash
mvn package –P profileTest1 
```

#### Spring profile 的用法
&ensp;&ensp;&ensp;&ensp;一般写各自的配置文件，比如dev.properties,test.properties等等，一般放到自建的 config目录下，或者resource目录也行

&ensp;&ensp;&ensp;&ensp;激活有两者用法

&ensp;&ensp;&ensp;&ensp;一是在全局的application.properties里指定，如下：

```java
#properties格式
spring.profiles.active=dev
```

&ensp;&ensp;&ensp;&ensp;二是启动参数传入，如下：

```java
--spring.profiles.active=dev
```