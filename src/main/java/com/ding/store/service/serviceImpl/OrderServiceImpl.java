package com.ding.store.service.serviceImpl;

import com.ding.store.entity.Address;
import com.ding.store.entity.Order;
import com.ding.store.entity.OrderItem;
import com.ding.store.mapper.OrderMapper;
import com.ding.store.service.AddressService;
import com.ding.store.service.CartService;
import com.ding.store.service.OrderService;
import com.ding.store.service.ex.InsertException;
import com.ding.store.vo.CartVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private AddressService addressService;


    @Resource
    private CartService cartService;


    @Transactional
    @Override
    public Order create(Integer aid, Integer[] cids, Integer uid, String username) {


        // 根据cids查询所勾选的购物车列表中的数据
        List<CartVO> carts = cartService.getVOByCids(uid, cids);

        // 计算这些商品的总价
        long totalPrice = 0;
        for (CartVO cart : carts) {
            totalPrice += cart.getRealPrice() * cart.getNum();
        }

        // 创建订单数据对象
        Order order = new Order();
        // 补全数据：uid
        order.setUid(uid);
        // 查询收货地址数据
        Address address = addressService.getByAid(aid, uid);
        // 补全数据：收货地址相关
        order.setName(address.getName());
        order.setPhone(address.getPhone());
        order.setAddress(address.getAddress());
        // 补全数据：totalPrice
        order.setPrice((int) totalPrice);
        // 补全数据：status
        order.setStatus(0);
        // 补全数据：下单时间
        order.setOrderTime(new Date());
        // 补全数据：日志
        order.setCreatedUser(username);
        order.setCreatedTime(new Date());
        order.setModifiedUser(username);
        order.setModifiedTime(new Date());
        // 插入订单数据
        Integer rows1 = orderMapper.insertOrder(order);
        if (rows1 != 1) {
            throw new InsertException("插入订单数据时出现未知错误，请联系系统管理员");
        }

        // 遍历carts，循环插入订单商品数据
        for (CartVO cart : carts) {
            // 创建订单商品数据
            OrderItem item = new OrderItem();
            // 补全数据：setOid(order.getOid())
            item.setOid(order.getOid());
            // 补全数据：pid, title, image, price, num
            item.setGid(cart.getGid());
            item.setTitle(cart.getTitle());
            item.setImage(cart.getImage());
            item.setPrice(cart.getRealPrice());
            item.setNum(cart.getNum());
            // 补全数据：4项日志
            item.setCreatedUser(username);
            item.setCreatedTime(new Date());
            item.setModifiedUser(username);
            item.setModifiedTime(new Date());
            // 插入订单商品数据
            Integer rows2 = orderMapper.insertOrderItem(item);
            if (rows2 != 1) {
                throw new InsertException("插入订单商品数据时出现未知错误，请联系系统管理员");
            }
        }

        // 返回
        return order;
    }
}
