package com.ding.store.service;

import com.ding.store.entity.DictDistrict;

import java.util.List;

public interface DictDistrictService {


    /**
     * 根据省/市/区的行政代号获取省/市/区的名称
     * @param code 省/市/区的行政代号
     * @return 匹配的省/市/区的名称，如果没有匹配的数据则返回null
     */
    String getNameByCode(String code);

    List<DictDistrict> getByParent(String parent);
}
