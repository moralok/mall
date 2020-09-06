# mall

## 数据库
### Mysql
1. 持久层框架 MyBatis Plus

### Redis
1. 分布式锁框架 Redisson
2. 对象序列化存储。Serializable、JsonDeserialize、JsonSerialize
3. Lettuce 连接池

### ElasticSearch
1. 使用 RestHighLevelClient 作为客户端

- 使用 Repository 简洁方便但是欠缺灵活性和扩展性
- Registering converter from class org.springframework.data.geo.Point to interface java.util.Map as writing converter although it doesn't convert to a store-supported type! You might want to check your annotation setup at the converter implementation. 目前只能忽略这些警告

### Rabbitmq
- 延时消息不能接受变动的时间（阿里云AMQP高级特性支持）

## 单元测试框架
### JUnit

## 接口文档框架 Swagger
1. ApiModel
2. ApiModelProperty

- 如何处理接口使用通用返回对象，其中的 data 为泛型
- 像创建和更新的DTO中的属性是否必填互不相同，必须使用两个DTO了？

## 安全框架
#### Shiro
1. 验证和授权配置
2. 开启注解 AuthorizationAttributeSourceAdvisor
3. 使用权限注解时 swagger 无法读取控制器 
4. 使用redis实现session分布式存储
5. 前后端项目禁用登录重定向，重写过滤器，返回 JSON
6. 密码匹配器
7. 密码加密

- 如何优雅地处理用户-角色-权限这样的关联查询
- session在redis中的序列化问题（shiro会额外添加属性导致GenericJackson2JsonRedisSerializer反序列化失败）
- 频繁访问redis问题，shiro-redis作者使用ThreadLocal优化
- 未登录的情况下，不会进控制器

## JavaBean 转换框架 MapStruct

1. [Lombok 共用问题](https://mapstruct.org/faq/#can-i-use-mapstruct-together-with-project-lombok)

## Spring 常用的特性

### 统一的数据校验
1. javax.validation 和 org.hibernate.validator
2. @Valid 和 @Validation（对前者的封装）
||Valid|Validation|
|-----|-----|-----|
|级联|有|无|
|分组|无|有|
|级别|方法、字段、构造器、参数|类、方法、参数|
3. 自定义参数校验

- 数据绑定
- 本地化
- @Repeatable(List.class)
- ConstraintValidatorContext

### 参数转换器
1. 实现 Converter
2. 使用 @Component
3. DateTimeFormat 和 JsonFormat 格式化日期

- 原始的enum名称转换仍然有效

### 事务
1. @Transactional(rollbackFor)

### Aspect
1. 日志系统
2. 统一的异常处理
3. 统一的接口返回。使用 ResponseBodyAdvice 避免控制器方法的返回值为统一返回结果，从而使接口更清晰，swagger文档更清楚。

## 业务

### 订单流程
1. 快速下单
对应的业务场景：在一些购物场景（例如，特价、秒杀）中，促使用户跳过购物车环节，立即下单。

### 商品
1. 列表层次的sku属性如何设计
