package com.ding.store.mapper;

import com.ding.store.entity.Order;
import com.ding.store.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    Integer insertOrder(Order order);

    Integer insertOrderItem(OrderItem item);
}
