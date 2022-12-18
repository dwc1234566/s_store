package com.ding.store.mapper;

import com.ding.store.entity.Cart;
import com.ding.store.vo.CartVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface CartMapper {
    /**
     * 查询某用户的购物车数据
     * @param uid 用户id
     * @return 该用户的购物车数据的列表
     */
    List<CartVO> findVOByUid(Integer uid);

    /**
     * 根据若干个购物车数据id查询详情的列表
     * @param cids 若干个购物车数据id
     * @return 匹配的购物车数据详情的列表
     */
    List<CartVO> findVOByCids(Integer[] cids);

    Cart findByUidAndGid(Integer uid, Integer gid);

    Integer insert(Cart cart);

    Integer updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);

    @Select("select  * from t_cart where cid = #{cid}")
    Cart findByCid(Integer cid);


    @Delete("DELETE FROM t_cart where cid = #{cid}")
    Integer deleteById(Integer cid);

    Integer deleteByIds(Integer[] cids);
}
