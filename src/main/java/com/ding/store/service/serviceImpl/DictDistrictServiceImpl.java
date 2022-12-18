package com.ding.store.service.serviceImpl;

import com.ding.store.entity.DictDistrict;
import com.ding.store.mapper.DictDistrictMapper;
import com.ding.store.service.DictDistrictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class DictDistrictServiceImpl implements DictDistrictService {

    @Resource
    private DictDistrictMapper dictDistrictMapper;

    @Override
    public String getNameByCode(String code) {
        return dictDistrictMapper.findNazmeByCode(code);
    }

    @Override
    public List<DictDistrict> getByParent(String parent) {
        List<DictDistrict> list = dictDistrictMapper.findByParent(parent);
        for (DictDistrict district : list) {
            district.setId(null);
            district.setParent(null);
        }
        return list;
    }
}
