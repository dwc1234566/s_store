package com.ding.store.mapper;

import com.ding.store.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsMapper {
    List<Goods> findHotList();

    List<Goods> findNewList();

    @Select("select * from t_goods where id = #{id}")
    Goods findById(Integer id);
}
