package com.ding.store.service.serviceImpl;

import com.ding.store.mapper.GoodsCategoryMapper;
import com.ding.store.service.GoodsCategoryService;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class GoodsCategoryImpl implements GoodsCategoryService {


    @Resource
    private GoodsCategoryMapper goodsCategoryMapper;
}
