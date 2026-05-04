# 用户订单管理系统

一个基于 Spring Boot 的简单后端练习项目，实现了用户注册、订单创建和查询功能，加入了 Redis 缓存优化。

## 技术栈

Spring Boot + MySQL + MyBatis-Plus + Redis + Maven + JDK 17

## 实现了哪些功能

用户注册，做了用户名查重
根据 ID 查用户，加了 Redis 缓存
创建订单，会先检查用户是否存在
查某个用户的所有订单，也加了缓存
统一的接口返回格式和异常处理

## 接口

### 查用户
GET /user/1

### 注册用户
POST /user/register
Body: { "username": "xxx", "phone": "xxx" }

### 创建订单
POST /order/create
Body: { "userId": 1, "productName": "xxx", "price": 99.00 }

### 查用户订单
GET /order/user/1

## 怎么跑起来

1. 装好 JDK 17、MySQL、Redis
2. 建一个 order_system 库，执行建表语句
3. 改 application.yml 里的数据库密码
4. 跑 OrderSystemApplication 的 main 方法就行
