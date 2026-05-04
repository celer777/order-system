package com.example.order_system.service;

import com.example.order_system.entity.User;

public interface UserService {
    User register(User user);
    User getById(Long id);
}