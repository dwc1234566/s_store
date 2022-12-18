package com.ding.store.intercepter;

import cn.hutool.core.bean.BeanUtil;

import com.ding.store.entity.User;
import com.ding.store.entity.UserDto;
import com.ding.store.mapper.UserMapper;
import com.ding.store.util.UserHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;


public class RefreshIntercepter implements HandlerInterceptor {


    private UserMapper userMapper;

    public RefreshIntercepter(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Integer uid = (Integer)request.getSession().getAttribute("uid");
        if (Objects.isNull(uid)){
            response.sendRedirect("/web/login.html");
            return false;
        }
        User user = userMapper.findUserById(uid);
        UserDto userDto = BeanUtil.copyProperties(user, UserDto.class);
        UserHolder.saveUser(userDto);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.removeUser();
    }
}
