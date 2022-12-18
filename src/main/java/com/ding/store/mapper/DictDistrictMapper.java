package com.ding.store.mapper;

import com.ding.store.entity.DictDistrict;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DictDistrictMapper {


    /**
     * 获取全国所有省/某省所有市/某市所有区
     * @param parent 父级代号，当获取某市所有区时，使用市的代号；当获取省所有市时，使用省的代号；当获取全国所有省时，使用"86"作为父级代号
     * @return 全国所有省/某省所有市/某市所有区的列表
     */
    List<DictDistrict> findByParent(String parent);
    /**
     * 根据省/市/区的行政代号获取省/市/区的名称
     * @param code 省/市/区的行政代号
     * @return 匹配的省/市/区的名称，如果没有匹配的数据则返回null
     */
    String findNazmeByCode(String code);

}
