package com.ding.store.controller;


import com.ding.store.service.GoodsService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class GoodsController {
    @Resource
    private GoodsService goodsService;
}
