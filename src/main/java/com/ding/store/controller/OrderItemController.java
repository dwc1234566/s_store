package com.ding.store.controller;


import com.ding.store.service.OrderService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/orderItem")
public class OrderItemController extends BaseController {
    @Resource
    private OrderService orderService;
}
