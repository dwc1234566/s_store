package com.ding.store.service.serviceImpl;

import com.ding.store.entity.Address;
import com.ding.store.mapper.AddressMapper;
import com.ding.store.service.AddressService;
import com.ding.store.service.DictDistrictService;
import com.ding.store.service.ex.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressMapper addressMapper;


    @Resource
    private DictDistrictService dictDistrictService;


    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        return list;
    }

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {

        // 根据参数uid调用addressMapper的countByUid(Integer uid)方法，统计当前用户的收货地址数据的数量
        Integer count = addressMapper.countByUid(uid);
        // 判断数量是否达到上限值
        Integer maxCount = 20;
        if (count > maxCount) {
            // 是：抛出AddressCountLimitException
            throw new AddressCountLimitException("收货地址数量已经达到上限(" + maxCount + ")！");
        }

        // 补全数据：省、市、区的名称
        String provinceName = dictDistrictService.getNameByCode(address.getProvince());
        String cityName = dictDistrictService.getNameByCode(address.getCity());
        String areaName = dictDistrictService.getNameByCode(address.getArea());
        address.setDistrict(provinceName+","+cityName+","+areaName);

        // 补全数据：将参数uid封装到参数address中
        address.setUid(uid);
        // 补全数据：根据以上统计的数量，得到正确的isDefault值(是否默认：0-不默认，1-默认)，并封装
        Integer isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);
        // 补全数据：4项日志
        Date now = new Date();
        address.setCreatedUser(username);
        address.setCreatedTime(now);
        address.setModifiedUser(username);
        address.setModifiedTime(now);

        // 调用addressMapper的insert(Address address)方法插入收货地址数据，并获取返回的受影响行数
        Integer rows = addressMapper.insert(address);
        // 判断受影响行数是否不为1
        if (rows != 1) {
            // 是：抛出InsertException
            throw new InsertException("插入收货地址数据时出现未知错误，请联系系统管理员！");
        }
    }

    @Override
    public Address getByAid(Integer aid, Integer uid) {
        // 根据收货地址数据id，查询收货地址详情
        Address address = addressMapper.findByAid(aid);

        if (address == null) {
            throw new AddressNotFoundException("尝试访问的收货地址数据不存在");
        }
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        address.setProvince(null);
        address.setCity(null);
        address.setArea(null);
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        return address;
    }

    @Override
    public void deleteAddressById(Integer aid) {
      Integer res =   addressMapper.deleteById(aid);
      if (res!=1){
          throw new DeleteException("删除地址失败");
      }
    }
}
