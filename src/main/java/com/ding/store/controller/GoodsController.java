package com.ding.store.controller;


import com.ding.store.entity.Goods;
import com.ding.store.service.GoodsService;
import com.ding.store.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController{
    @Resource
    private GoodsService goodsService;



    @GetMapping("hot")
    public Result hot(){
        List<Goods> data = goodsService.findHotList();
        return Result.ok(data);
    }



    @GetMapping("new")
    public Result newGoods(){
       List<Goods> data =  goodsService.findNewList();
        return Result.ok(data);
    }




    @GetMapping("{id}/details")
    public Result getById(@PathVariable("id") Integer id) {
        // 调用业务对象执行获取数据
       Goods data = goodsService.findById(id);
        // 返回成功和数据
        return Result.ok(data);
    }
}
