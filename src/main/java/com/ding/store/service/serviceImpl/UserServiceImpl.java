package com.ding.store.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.digest.DigestUtil;
import com.ding.store.entity.User;
import com.ding.store.entity.UserDto;
import com.ding.store.mapper.UserMapper;
import com.ding.store.service.UserService;
import com.ding.store.service.ex.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.Date;

import static com.ding.store.util.RegexUtils.isEmailInvalid;
import static com.ding.store.util.RegexUtils.isPhoneInvalid;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    @Transactional
    public UserDto login(String username, String password) {
        User user = userMapper.findByUserName(username);
        if (user==null){
            throw new UserNotFoundException("用户不存在");
        }
        if (user.getIsDelete()==1){
            throw new UsernameDuplicationException("用户已经删除");
        }
        if (!getMd5Password(password,user.getSalt()).equals(user.getPassword())){
            throw new PasswordNotMatchException("密码错误");
        }
        return BeanUtil.copyProperties(user, UserDto.class);
    }

    @Override
    public void addUser(User user) {
        User result = userMapper.findByUserName(user.getUsername());
        if (result!=null){
            throw new UsernameDuplicationException("用户名已经存在");
        }
        //uuid生成salt
        UUID salt = UUID.randomUUID(true);
        user.setSalt(salt.toString());
        user.setPassword(getMd5Password(user.getPassword(),salt.toString()));
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setCreatedTime(new Date());
        user.setModifiedTime(new Date());
        user.setModifiedUser(user.getUsername());
        Integer res = userMapper.insertUser(user);
        if (res!=1){
            throw new InsertException("用户增加失败");
        }

    }

    @Override
    public void updatePassword(Integer uid, String oldPassword, String newPassword) throws UserNotFoundException, PasswordNotMatchException, UpdateException {
        User user = queryUserById(uid);

        if (!user.getPassword().equals(getMd5Password(oldPassword,user.getSalt())))
        {
            throw new PasswordNotMatchException("原始密码错误");
        }
        user.setPassword(getMd5Password(newPassword,user.getSalt()));
        user.setModifiedTime(new Date());
        Integer res = userMapper.updatePassword(
                user.getUid(),getMd5Password(newPassword,user.getSalt()), user.getUsername(),new Date());
        if (res!=1){
            throw new UpdateException("修改密码异常");
        }
    }





    @Override
    public User updateUserInfo(User user) throws UpdateException {
        if (!user.getPhone().isEmpty()){
            if (!isPhoneInvalid(user.getPhone())){
                throw new ServiceException("手机号格式不正确");
            }
        }
        if (!user.getEmail().isEmpty()){
            if (!isEmailInvalid(user.getEmail())){
                throw new ServiceException("邮箱格式不正确");
            }
        }
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(new Date());
        Integer res = userMapper.updateInfo(user);
        if (res!=1){
            throw new UpdateException("修改用户异常");
        }
        return queryUserById(user.getUid());
    }




    @Override
    public User updateAvatar(Integer uid, String avatar) {
        User user = queryUserById(uid);
        Integer res = userMapper.updateAvatar(uid, avatar, user.getUsername(), new Date());
        if (res!=1){
            throw new UpdateException("修改头像异常");
        }
        user.setAvatar(avatar);
        return user;
    }


    @Override
    public User queryUserById(Integer uid) {
        User user = userMapper.findUserById(uid);
        if (user==null){
            throw new UserNotFoundException("用户不存在");
        }
        if (user.getIsDelete()==1){
            throw new UsernameDuplicationException("用户已经冻结");
        }
        return user;
    }


    public String getMd5Password(String password,String salt){
        String s = salt+password+salt;
        for (int i = 0; i < 5; i++) {
            s = DigestUtil.md5Hex16(s);
        }
        return s;
    }
}
