package com.ding.store.controller;


import com.ding.store.service.DictDistrictService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class DictDistrictController {


    @Resource
    private DictDistrictService dictDistrictService;
}
