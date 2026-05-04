package com.example.order_system.service;

import com.example.order_system.entity.Order;
import java.util.List;

public interface OrderService {
    Order create(Order order);
    List<Order> getByUserId(Long userId);
}