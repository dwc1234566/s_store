package com.ding.store.controller;


import com.ding.store.entity.Order;
import com.ding.store.service.OrderService;
import com.ding.store.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController{
    @Resource
    private OrderService orderService;





    @RequestMapping("create")
    public Result create(Integer aid, Integer[] cids, HttpSession session) {
        // 从Session中取出uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行业务
        Order data = orderService.create(aid, cids, uid, username);
        // 返回成功与数据
        return Result.ok(data);
    }
}
