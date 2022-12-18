package com.ding.store.controller;

import com.ding.store.service.CartService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class CartController {

    @Resource
    private CartService cartService;
}
