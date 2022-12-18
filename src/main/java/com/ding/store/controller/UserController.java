package com.ding.store.controller;


import com.ding.store.service.UserService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class UserController {
    @Resource
    private UserService userService;
}
