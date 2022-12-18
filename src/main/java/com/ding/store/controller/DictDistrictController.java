package com.ding.store.controller;


import com.ding.store.entity.DictDistrict;
import com.ding.store.service.DictDistrictService;
import com.ding.store.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/districts")
public class DictDistrictController extends BaseController {


    @Resource
    private DictDistrictService dictDistrictService;


    @GetMapping({"", "/"})
    //@RequestMapping(method={RequestMethod.GET})
    public Result getByParent(String parent) {
        List<DictDistrict> data = dictDistrictService.getByParent(parent);
        return Result.ok(data);
    }

}
