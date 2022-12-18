package com.ding.store.controller;


import com.ding.store.service.AddressService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class AddressController {


    @Resource
    private AddressService addressService;
}
