package com.example.order_system.service.impl;

import com.example.order_system.entity.Order;
import com.example.order_system.mapper.OrderMapper;
import com.example.order_system.mapper.UserMapper;
import com.example.order_system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Order create(Order order) {
        if (userMapper.selectById(order.getUserId()) == null) {
            throw new RuntimeException("用户不存在");
        }
        orderMapper.insert(order);
        redisTemplate.delete("order:list:" + order.getUserId());
        return order;
    }

    public List<Order> getByUserId(Long userId) {
        Object cached = redisTemplate.opsForValue().get("order:list:" + userId);
        if (cached instanceof List<?>) {
            return (List<Order>) cached;
        }
        List<Order> list = orderMapper.selectByUserId(userId);
        if (list != null && !list.isEmpty()) {
            redisTemplate.opsForValue().set("order:list:" + userId, list, Duration.ofMinutes(10));
        }
        return list;
    }
}