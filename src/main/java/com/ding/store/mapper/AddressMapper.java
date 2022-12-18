package com.ding.store.mapper;

import com.ding.store.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AddressMapper {
    List<Address> findByUid(Integer uid);

    /**
     * 统计某用户的收货地址数据的数量
     * @param uid 用户的id
     * @return 该用户的收货地址数据的数量
     */
    Integer countByUid(@Param("uid") Integer uid);

    /**
     * 插入收货地址数据
     * @param address 收货地址数据
     * @return 受影响的行数
     */
    Integer insert(Address address);

    Address findByAid(Integer aid);

    Integer deleteById(Integer aid);

    /**
     * 将指定的收货地址设置为默认
     * @param aid 用户aid
     * @return 受影响行数
     */
    Integer updateDefault(Integer aid);



    /**
     * 通过用户uid查询最后一次修改的地址信息
     * @param uid 用户uid
     * @return 最后一次修改的地址信息
     */
    Address findLastModified(Integer uid);


    /**
     * 把所有默认地址设为非默认
     * @param uid	用户uid
     * @return 受影响行数
     */
    Integer updateNonDefault(Integer uid);
}
