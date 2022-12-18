package com.ding.store.controller;


import com.ding.store.service.GoodsCategoryService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;


@Controller
public class GoodsCategoryController {
    @Resource
    private GoodsCategoryService goodsCategoryService;
}
