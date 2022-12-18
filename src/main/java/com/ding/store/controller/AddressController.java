package com.ding.store.controller;


import com.ding.store.entity.Address;
import com.ding.store.service.AddressService;
import com.ding.store.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController extends BaseController{


    @Resource
    private AddressService addressService;


    @GetMapping({"", "/"})
    public Result getByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return Result.ok(data);
    }


    @RequestMapping("add_new_address")
    public Result addNewAddress( Address address, HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        // 调用业务对象的方法执行业务
        addressService.addNewAddress(uid, username, address);
        // 响应成功
        return Result.ok();
    }


    @PostMapping("/{aid}/delete")
    public Result delete(@PathVariable("aid")Integer aid){
        addressService.deleteAddressById(aid);
        return Result.ok();
    }









}
