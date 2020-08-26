# mall

## 日志系统
1. Aspect

## 数据库
### Mysql
1. 持久层框架 MyBatis Plus

### Redis
1. 分布式锁框架 Redisson
2. 对象序列化存储。Serializable、JsonDeserialize、JsonSerialize
3. Lettuce 连接池

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


## 订单流程
1. 快速下单
对应的业务场景：在一些购物场景（例如，特价、秒杀）中，促使用户跳过购物车环节，立即下单。


