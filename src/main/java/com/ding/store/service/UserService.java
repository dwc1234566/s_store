package com.ding.store.service;

import com.ding.store.entity.User;
import com.ding.store.entity.UserDto;
import com.ding.store.service.ex.PasswordNotMatchException;
import com.ding.store.service.ex.UpdateException;
import com.ding.store.service.ex.UserNotFoundException;
import com.sun.corba.se.spi.presentation.rmi.IDLNameTranslator;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserDto login(String username, String password);



    void addUser(User user);



    /**
     * 修改密码
     * @param uid 用户id
     * @param oldPassword 老密码
     * @param newPassword 新密码
     * @throws UserNotFoundException 用户名不存在
     * @throws PasswordNotMatchException	密码错误
     * @throws UpdateException	修改异常
     */
   void updatePassword(Integer uid,String oldPassword,String newPassword) throws UserNotFoundException, PasswordNotMatchException, UpdateException;




    /**
     * 跟新个人资料
     * @param user	用户信息
     * @throws UserNotFoundException 用户名不存在
     * @throws UpdateException	修改异常
     */
   User updateUserInfo(User user) throws UpdateException;


    /**
     * 更新头像
     * @param uid 用户id
     * @Avatar 用户头像
     * @throws UserNotFoundException 用户不存在
     * @throws UpdateException	修改异常
     */
    User updateAvatar(Integer uid, String avatar);




    /**
     * 根据用户id查询用户数据
     * @param uid 用户id
     * @return 匹配的用户数据，如果没有匹配的数据，则返回null
     */
    User queryUserById(Integer uid);
}
