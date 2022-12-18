package com.ding.store.controller;

import com.ding.store.service.CartService;

import com.ding.store.util.Result;
import com.ding.store.vo.CartIds;
import com.ding.store.vo.CartVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController extends BaseController{

    @Resource
    private CartService cartService;


    @GetMapping({"", "/"})
    public Result getVOByUid(HttpSession session) {
        // 从Session中获取uid
        Integer uid = getUidFromSession(session);
        // 调用业务对象执行查询数据
        List<CartVO> data = cartService.getVOByUid(uid);
        // 返回成功与数据
        return Result.ok(data);
    }



    @GetMapping("list")
    public Result getVOByCids(Integer[] cids, HttpSession session) {
        // 从Session中获取uid
        Integer uid = getUidFromSession(session);
        // 调用业务对象执行查询数据
        List<CartVO> data = cartService.getVOByCids(uid, cids);
        // 返回成功与数据
        return Result.ok(data);
    }


    @PostMapping("add_to_cart")
    public Result addToCart(Integer gid, Integer amount, HttpSession session) {
//        System.out.println("pid=" + gid);
//        System.out.println("amount=" + amount);
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行添加到购物车
        cartService.addToCart(uid, gid, amount, username);
        return Result.ok();
    }




    @RequestMapping("{cid}/num/add")
    public Result addNum(@PathVariable("cid") Integer cid, HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行增加数量
        Integer data = cartService.addNum(cid, uid, username);
        // 返回成功
        return Result.ok(data);
    }



    @RequestMapping("{cid}/num/reduce")
    public Result reduceNum(@PathVariable("cid") Integer cid, HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行增加数量
        Integer data = cartService.reduceNum(cid, uid, username);
        // 返回成功
        return Result.ok(data);
    }



    @RequestMapping("{cid}/delete")
    public Result delete(@PathVariable("cid") Integer cid) {
        cartService.deleteByCid(cid);
        // 返回成功
        return Result.ok();
    }


    @RequestMapping("/deleteList")
    public Result deleteList(CartIds cartIds) {
        System.out.println(Arrays.toString(cartIds.getCids()));

        cartService.deleteByCids(cartIds.getCids());


        // 返回成功
        return Result.ok();
    }

}
