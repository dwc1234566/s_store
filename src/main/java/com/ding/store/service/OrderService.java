package com.ding.store.service;

import com.ding.store.entity.Order;

public interface OrderService {
    Order create(Integer aid, Integer[] cids, Integer uid, String username);
}
