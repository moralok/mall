# mall

## 日志系统
1. Aspect
2. 统一的异常处理

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

### Rabbitmq
- 延时消息不能接受变动的时间（阿里云AMQP高级特性支持）

## 单元测试框架
### JUnit

## 接口文档框架
1. Swagger

## 安全框架
#### Shiro
1. 验证和授权配置
2. 开启注解
3. 使用权限注解时 swagger 无法读取控制器
4. 使用redis实现session分布式存储

- 如何优雅地处理用户-角色-权限这样的关联查询
- session在redis中的序列化问题（shiro会额外添加属性导致GenericJackson2JsonRedisSerializer反序列化失败）
- 频繁访问redis问题，shiro-redis作者使用ThreadLocal优化

## 统一的数据校验
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

## 订单流程
1. 快速下单
对应的业务场景：在一些购物场景（例如，特价、秒杀）中，促使用户跳过购物车环节，立即下单。


