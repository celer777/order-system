package com.example.order_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.order_system.entity.User;
import com.example.order_system.mapper.UserMapper;
import com.example.order_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public User register(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        if (userMapper.selectOne(wrapper) != null) {
            throw new RuntimeException("用户名已存在");
        }
        userMapper.insert(user);
        redisTemplate.opsForValue().set("user:" + user.getId(), user, Duration.ofMinutes(30));
        return user;
    }

    public User getById(Long id) {
        User user = (User) redisTemplate.opsForValue().get("user:" + id);
        if (user != null) {
            return user;
        }
        user = userMapper.selectById(id);
        if (user != null) {
            redisTemplate.opsForValue().set("user:" + id, user, Duration.ofMinutes(30));
        }
        return user;
    }
}