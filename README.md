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
2. 启动 MySQL, 随便建一个数据库, 并建立用户授权, 不会的看下面. (数据库的初始化程序会在启动时自动执行, 存在的问题在于对现有表的变更, 后面会专门针对此设计数据库的初始化方式, 已经大体有了几种想法和方法.)
3. 修改配置文件 `application.properties`, 拷贝重命名为 `application-{env}.properties`, `{env}` 为 Spring Profile.
4. 启动项目, 以IDEA为例, `Edit Configuration` -> `ADD Configuration` -> `Maven` -> 写自己的配置参数: `clean tomcat7:run -Dspring.profiles.active=dev`
5. 其他的IDE配置大同小异, 本质都是使用 `mvn clean tomcat7:run`.
6. 程序启动完毕, 访问 http://localhost:9999/index
7. MySQL创建用户与授权:
  1. 终端连接数据库 `mysql -u root -p <password>`, 这里 `<password>` 改为你的 root 密码, 如果不存在密码, 则不需要加 `-p` 参数
  2. 查看当前存在几个数据库 `SHOW DATABASES;`.
  3. 删除一个不需要的数据库 `DROP DATABASE <DATABASE>;`.
  4. 创建一个数据库 `CREATE DATABASE <DATABASE>;`
  5. 选择你想使用的数据库 `USE <DATABASE>;`, 这里 `<DATABASE›` 请改为上面的 DATABASES 列表中的表名, 也就是当前帐号可以使用的数据库.
  6. 创建一个用户 `CREATE USER '<username>'@'<host>' IDENTIFIED BY '<password>';`, eq: `CREATE USER 'acmeair'@'localhost' IDENTIFIED BY '123456';`.
  7. 给用户授予某个数据库的权限 `GRANT <privileges> ON <databaseName>.<tableName> TO '<username>'@'<host>';`, eq `GRANT ALL ON acmeair.* TO 'acmeair'@'localhost';`
  8. 授权生效 `FLUSH PRIVILEGES;`

## 四、项目配置方式

项目启动时, 真正加载配置文件的路径是`runtimecfg`, 但其实默认的项目中, 是什么都没有的. 通过 `maven-resources-plugin` [官网](http://maven.apache.org/plugins/maven-resources-plugin/) 实现多配置文件共存, 在打包时, 通过指定打包的环境参数, 将实际的配置文件拷贝于此文件夹中.

好处在于, 一套代码, 能在不修改任何配置参数的情况下, 同时兼容多种部署环境, 方便项目上线和代码持续集成.

## 五、Kong相关学习资料

1. [Kong官方文档](http://getkong.org/doc)
