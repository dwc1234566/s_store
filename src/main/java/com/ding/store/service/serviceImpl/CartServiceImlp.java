package com.ding.store.service.serviceImpl;

import com.ding.store.entity.Cart;
import com.ding.store.entity.Goods;
import com.ding.store.mapper.CartMapper;
import com.ding.store.service.CartService;
import com.ding.store.service.GoodsService;
import com.ding.store.service.ex.AccessDeniedException;
import com.ding.store.service.ex.CartNotFoundException;
import com.ding.store.service.ex.DeleteException;
import com.ding.store.service.ex.InsertException;
import com.ding.store.vo.CartVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImlp implements CartService {


    @Resource
    private CartMapper cartMapper;


    @Resource
    private GoodsService goodsService;

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

    @Override
    public List<CartVO> getVOByCids(Integer uid, Integer[] cids) {
        List<CartVO> list = cartMapper.findVOByCids(cids);
        /**
         for (CartVO cart : list) {
         if (!cart.getUid().equals(uid)) {
         list.remove(cart);
         }
         }
         */
        Iterator<CartVO> it = list.iterator();
        while (it.hasNext()) {
            CartVO cart = it.next();
            if (!cart.getUid().equals(uid)) {
                it.remove();
            }
        }
        return list;
    }

    @Override
    public void addToCart(Integer uid, Integer gid, Integer amount, String username) {
        // 根据参数pid和uid查询购物车中的数据
        Cart result = cartMapper.findByUidAndGid(uid, gid);
        Date now = new Date();
        // 判断查询结果是否为null
        if (result == null) {
            // 是：表示该用户并未将该商品添加到购物车
            // 创建Cart对象
            Cart cart = new Cart();
            // 封装数据：uid,pid,amount
            cart.setUid(uid);
            cart.setGid(Long.valueOf(gid));
            cart.setNum(amount);
            // 调用productService.findById(pid)查询商品数据，得到商品价格
            Goods goods = goodsService.findById(gid);

            // 封装数据：4个日志
            cart.setCreatedUser(username);
            cart.setCreatedTime(now);
            cart.setModifiedUser(username);
            cart.setModifiedTime(now);
            // 调用insert(cart)执行将数据插入到数据表中
            Integer rows = cartMapper.insert(cart);
            if (rows != 1) {
                throw new InsertException("插入商品数据时出现未知错误，请联系系统管理员");
            }
        } else {
            // 否：表示该用户的购物车中已有该商品
            // 从查询结果中获取购物车数据的id
            Integer cid = result.getCid();
            // 从查询结果中取出原数量，与参数amount相加，得到新的数量
            Integer num = result.getNum() + amount;
            // 执行更新数量
            Integer rows = cartMapper.updateNumByCid(cid, num, username, now);
            if (rows != 1) {
                throw new InsertException("修改商品数量时出现未知错误，请联系系统管理员");
            }
        }
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出CartNotFoundException
            throw new CartNotFoundException("尝试访问的购物车数据不存在");
        }
        // 判断查询结果中的uid与参数uid是否不一致
        if (!result.getUid().equals(uid)) {
            // 是：抛出AccessDeniedException
            throw new AccessDeniedException("非法访问");
        }

        // 可选：检查商品的数量是否大于多少(适用于增加数量)或小于多少(适用于减少数量)
        // 根据查询结果中的原数量增加1得到新的数量num
        Integer num = result.getNum() + 1;

        // 创建当前时间对象，作为modifiedTime
        Date now = new Date();
        // 调用updateNumByCid(cid, num, modifiedUser, modifiedTime)执行修改数量
        Integer rows = cartMapper.updateNumByCid(cid, num, username, now);
        if (rows != 1) {
            throw new InsertException("修改商品数量时出现未知错误，请联系系统管理员");
        }
        // 返回新的数量
        return num;
    }

    @Override
    public Integer reduceNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出CartNotFoundException
            throw new CartNotFoundException("尝试访问的购物车数据不存在");
        }
        // 判断查询结果中的uid与参数uid是否不一致
        if (!result.getUid().equals(uid)) {
            // 是：抛出AccessDeniedException
            throw new AccessDeniedException("非法访问");
        }

        // 可选：检查商品的数量是否大于多少(适用于增加数量)或小于多少(适用于减少数量)
        // 根据查询结果中的原数量增加1得到新的数量num
        Integer num = result.getNum() - 1;

        // 创建当前时间对象，作为modifiedTime
        Date now = new Date();
        // 调用updateNumByCid(cid, num, modifiedUser, modifiedTime)执行修改数量
        Integer rows = cartMapper.updateNumByCid(cid, num, username, now);
        if (rows != 1) {
            throw new InsertException("修改商品数量时出现未知错误，请联系系统管理员");
        }
        // 返回新的数量
        return num;
    }

    @Override
    public void deleteByCid(Integer cid) {
        Integer res = cartMapper.deleteById(cid);
        if (res!=1){
            throw  new DeleteException("从购物车删除商品失败");
        }
    }

    @Override
    public void deleteByCids(Integer[] cids) {
        Integer res = cartMapper.deleteByIds(cids);
        if (res != cids.length){
            throw new DeleteException("从购物车删除商品失败");
        }
    }
}
