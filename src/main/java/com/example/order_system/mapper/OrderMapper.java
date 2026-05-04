package com.example.order_system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.order_system.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    @Select("SELECT * FROM order_t WHERE user_id = #{userId}")
    List<Order> selectByUserId(Long userId);
}