package com.ding.store.service.serviceImpl;

import com.ding.store.mapper.OrderItemMapper;
import com.ding.store.service.OrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Resource
    private OrderItemMapper orderItemMapper;
}
