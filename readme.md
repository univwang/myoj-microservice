# MyOJ在线评测系统

[在线测试地址](https://oj.univwang.top/)

## 系统架构
- 根据功能职责，将系统划分为负责核心业务的后端模块、负责校验结果的判题模块、负责编译执行代码的可复用代码沙箱。各模块相互独立，并通过 API 接口和分包的方式实现协作。

## 库表设计
- 根据业务流程自主设计用户表、题目表、题目提交表，并通过给题目表添加 userId 索引提升检索性能。

## 判题机模块架构
- 自主设计判题机模块的架构，定义了代码沙箱的抽象调用接口和多种实现类（比如远程/第三方代码沙箱），并通过静态工厂模式 + Spring 配置化的方式实现了对多种代码沙箱的灵活调用。
- 使用代理模式对代码沙箱接口进行能力增强，统一实现了对代码沙箱调用前后的日志记录，减少重复代码。
- 由于判题逻辑复杂、且不同题目的判题算法可能不同（比如 Java 题目额外增加空间限制），选用策略模式代替 if else 独立封装了不同语言的判题算法，提高系统的可维护性。
- 使用 Java Runtime 对象的 exec 方法实现了对 Java 程序的编译和执行，并通过 Process 类的输入流获取执行结果，实现了 Java 原生代码沙箱。
- 通过编写 Java 脚本自测代码沙箱，模拟了多种程序异常情况并针对性解决，如使用守护线程 + Thread.sleep 等待机制实现了对进程的超时中断、使用 JVM -Xmx 参数限制用户程序占用的最大堆内存、使用黑白名单 + 字典树的方式实现了对敏感操作的限制。（选1-2种即可）
- 使用 Java 安全管理器和自定义的 Security Manager 对用户提交的代码进行权限控制，比如关闭写文件、执行文件权限，进一步提升了代码沙箱的安全性。
- 为保证沙箱宿主机的稳定性，选用 Docker 隔离用户代码，使用 Docker Java 库创建容器隔离执行代码，并通过 tty 和 Docker 进行传参交互，从而实现了更安全的代码沙箱。
- 使用 VMware Workstation 虚拟机软件搭建 Ubuntu Linux + Docker 环境，并通过 JetBrains Client 连接虚拟机进行实时远程开发，提高了开发效率。
- 为提高 Docker 代码沙箱的安全性，通过 HostConfig 限制了容器的内存限制和网络隔离，并通过设置容器执行超时时间解决资源未及时释放的问题。
- 由于 Java 原生和 Docker 代码沙箱的实现流程完全一致（编译、执行、获取输出、清理），选用模板方法模式定义了一套标准的流程并允许子类自行扩展部分流程，提高代码一致性并大幅简化冗余代码。
- 为防止用户恶意请求代码沙箱服务，给调用方分配签名密钥，并通过校验请求头中的密钥保证了 API 调用安全。
- 为保证项目各模块的稳定性，选用 Spring Cloud Alibaba 重构单体项目，划分为用户服务、题目服务、判题服务、公共模块。
- 使用阿里云原生脚手架初始化微服务项目，并结合 Maven 子父模块的配置，保证了微服务各模块依赖的版本一致性，避免依赖冲突。
- 通过工具（JetBrains 的 Find Usage 功能 + 表格整理）梳理微服务间的调用关系，并通过 Nacos + OpenFeign 实现了各模块之间的相互调用，如判题服务调用题目服务来获取题目信息。
- 使用 Spring Cloud Gateway 对各服务接口进行聚合和路由，保护服务的同时简化了客户端的调用（前端不用根据业务请求不同端口的服务），并通过自定义 CorsWebFilter Bean 全局解决了跨域问题。
- 使用 Knife4j Gateway 在网关层实现了对各服务 Swagger 接口文档的统一聚合，无需通过切换地址查看各服务的文档。
- 为保护内部服务接口，给接口路径统一设置inner前缀，并通过在网关自定义 GlobalFilter（全局请求拦截器）实现对内部请求的检测和拦截，集中解决了权限校验问题。
- 为防止判题操作执行时间较长，系统选用异步的方式，在题目服务中将用户提交id发送给 RabbitMQ 消息队列，并通过 Direct 交换机转发给判题队列，由判题服务进行消费，异步更新提交状态。相比于同步，响应时长减少，且系统 qps 提升。


## docker 打包命令

```shell
scp ./myoj-backend-user-service/target/myoj-backend-user-service-0.0.1-SNAPSHOT.jar root@10.160.1.244:/root/project/myoj/myoj-backend-user-service
scp ./myoj-backend-question-service/target/myoj-backend-question-service-0.0.1-SNAPSHOT.jar root@10.160.1.244:/root/project/myoj/myoj-backend-question-service
scp ./myoj-backend-judge-service/target/myoj-backend-judge-service-0.0.1-SNAPSHOT.jar root@10.160.1.244:/root/project/myoj/myoj-backend-judge-service
scp ./myoj-backend-gateway/target/myoj-backend-gateway-0.0.1-SNAPSHOT.jar root@10.160.1.244:/root/project/myoj/myoj-backend-gateway

```