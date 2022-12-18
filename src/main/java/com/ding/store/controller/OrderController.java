package com.ding.store.controller;


import com.ding.store.service.OrderService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class OrderController {
    @Resource
    private OrderService orderService;
}
