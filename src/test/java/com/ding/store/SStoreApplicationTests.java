package com.ding.store;

import com.ding.store.entity.User;
import com.ding.store.mapper.UserMapper;
import com.ding.store.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SStoreApplicationTests {

   @Resource
   UserService userService;

    @Resource
    UserMapper userMapper;


    @Test
    void contextLoads() {
        List<User> list = userMapper.list();
        for (User user : list) {
            System.out.println(user.getModifiedUser());
        }
    }


    @Test
    void updatePassword(){
        User user = new User();
        user.setUid(12);
        user.setEmail("3189577591@qq.com");
        user.setGender(1);
        user.setPhone("12345678900");
        userService.updateUserInfo(user);
    }
    @Test
    void updatePassword1(){
       userService.updatePassword(12,"111","123456");
    }

}
