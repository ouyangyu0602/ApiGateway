## Description

项目启动时, 真正加载配置文件的路径, 但其实默认的项目中, 是什么都没有的. 通过 `maven-resources-plugin` [官网](http://maven.apache.org/plugins/maven-resources-plugin/) 实现多配置文件共存, 在打包时, 通过指定打包的环境参数, 将实际的配置文件拷贝于此文件夹中.

好处在于, 一套代码, 能在不修改任何配置参数的情况下, 同时兼容多种部署环境, 方便项目上线和代码持续集成.
