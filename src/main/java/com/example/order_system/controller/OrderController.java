package com.example.order_system.controller;

import com.example.order_system.common.Result;
import com.example.order_system.entity.Order;
import com.example.order_system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public Result<Order> create(@RequestBody Order order) {
        return Result.success(orderService.create(order));
    }

    @GetMapping("/user/{userId}")
    public Result<List<Order>> getByUserId(@PathVariable Long userId) {
        return Result.success(orderService.getByUserId(userId));
    }
}