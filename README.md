# Bit Chat (开发中)
Bit Chat 是一个分布式中继加密通信应用. 

通过在客户端之间中继,不经过第三方服务器通信,隐藏连接来保护您在互联网上的隐私.

___


## 当前版本 

### v0.0.3
#### 客户端

当前客户端具有两个界面

* 命令行界面


* 图形界面

* 共有功能
     1. 服务器端登录
     2. 同局域网下使用Ipv4地址互联,无需服务器端登录
     3. 互联网中使用Ipv6地址互联,双方具有ipv6的条件下.
     4. 查看本地用户表.登录服务器端时获得
     5. 请求方发起会话请求时可以使用用户表中用户名,也可以使用对方相应地址和端口号连接

#### 服务端
* 作为目录服务器,为客户端存储用户表
* 功能
     1. 监听客户端上线请求
     2. 将客户端上线请求存储在数据库中
     3. 返回当前数据库中的用户表
___


## 文件目录
     |-- src -- |--  main  --| -- java -- |-- Client1 客户端命令行界面
     |                       |            |
     |                       |            |-- logos 欢迎界面标志
     |                       |            |
     |                       |            |-- Server 服务端
     |                       |            |
     |                       |            |-- UI.controller 客户端图形界面
     |                       |            |
     |                       |            |-- util 工具类
     |                       |
     |                       |-- resources
     |
     |--  一些配置文件和日志文件,自动生成,无需配置
