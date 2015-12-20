# Kong 配置管理后台

基于Kong的Restful协议，对Kong进行管理的后台系统。

## 一、开发环境

1. JDK 8 (Openjdk or Oracle JDK)
2. Maven 3.3.9 (Higher than this version)
3. IDE: Intellij IDEA, Eclipse, Netbeans
4. MySQL 5.2 or higher
5. Git 2.x
6. Kong 5.x (会提供阿里云在线版，也可自己搭建)
7. Redis

## 二、部署开发

1. 导入IDE, 等待依赖导入完毕。或者在命令行中使用 `mvn clean eclipse:eclipse` 转换为eclipse可以导入的项目.
2. 启动 MySQL, 随便建一个数据库, 并建立用户授权, 你要使用root账户也行. (数据库的初始化程序会在启动时自动执行, 存在的问题在于对现有表的变更, 后面会专门针对此设计数据库的初始化方式, 已经大体有了几种想法和方法.)
3. 修改配置文件 拷贝 `resource/runtimecfg-template` 里面的文件至 `resource/runtimecfg-dev` 下面, 修改文件的配置.
4. 启动项目, 以IDEA为例, `Edit Configuration` -> `ADD Configuration` -> `Maven` -> 写自己的配置参数见下图
![配置界面](http://git.oschina.net/syhily/apisystem/raw/master/docs/maven-config.png)
5. 其他的IDE配置大同小异, 本质都是使用 `mvn clean tomcat7:run`.
6. 程序启动完毕, 访问 http://localhost:9999/index

## 三、项目配置方式

项目启动时, 真正加载配置文件的路径是`runtimecfg`, 但其实默认的项目中, 是什么都没有的. 通过 `maven-resources-plugin` [官网](http://maven.apache.org/plugins/maven-resources-plugin/) 实现多配置文件共存, 在打包时, 通过指定打包的环境参数, 将实际的配置文件拷贝于此文件夹中.

好处在于, 一套代码, 能在不修改任何配置参数的情况下, 同时兼容多种部署环境, 方便项目上线和代码持续集成.

## 四、Kong相关学习资料

1. [Kong官网](http://getkong.org)

**All Rights Reserved By Yufan**
