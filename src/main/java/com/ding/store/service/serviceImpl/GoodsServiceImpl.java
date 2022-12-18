package com.ding.store.service.serviceImpl;

import com.ding.store.entity.Goods;
import com.ding.store.mapper.GoodsMapper;
import com.ding.store.service.GoodsService;
import com.ding.store.service.ex.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Transactional
    @Override
    public List<Goods> findHotList() {
        List<Goods> list = goodsMapper.findHotList();
        return list;
    }

    @Transactional
    @Override
    public List<Goods> findNewList() {
        List<Goods> list =  goodsMapper.findNewList();
        return list;
    }

    @Override
    public Goods findById(Integer id) {
        // 根据参数id调用私有方法执行查询，获取商品数据
        Goods goods = goodsMapper.findById(id);
        // 判断查询结果是否为null
        if (goods == null) {
            // 是：抛出ProductNotFoundException
            throw new ProductNotFoundException("尝试访问的商品数据不存在");
        }
        // 将查询结果中的部分属性设置为null
        goods.setPriority(null);
        goods.setCreatedUser(null);
        goods.setCreatedTime(null);
        goods.setModifiedUser(null);
        goods.setModifiedTime(null);
        // 返回查询结果
        return goods;
    }

}
