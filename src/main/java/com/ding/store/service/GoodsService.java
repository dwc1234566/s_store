package com.ding.store.service;

import com.ding.store.entity.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> findHotList();



    List<Goods> findNewList();



    /**
     * 根据商品id查询商品详情
     * @param id 商品id
     * @return 匹配的商品详情，如果没有匹配的数据则返回null
     */
    Goods findById(Integer id);

}
