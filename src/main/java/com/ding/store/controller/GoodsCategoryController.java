package com.ding.store.controller;


import com.ding.store.service.GoodsCategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/goodsCategory")
public class GoodsCategoryController extends BaseController {
    @Resource
    private GoodsCategoryService goodsCategoryService;
}
