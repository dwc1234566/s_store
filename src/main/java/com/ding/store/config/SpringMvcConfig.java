package com.ding.store.config;


import com.ding.store.intercepter.RefreshIntercepter;
import com.ding.store.mapper.UserMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    UserMapper userMapper;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 白名单
        List<String> patterns = new ArrayList<String>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/districts/**");
        patterns.add("/products/**");

      registry.addInterceptor(new RefreshIntercepter(userMapper)).addPathPatterns("/**").excludePathPatterns(patterns);
    }
}
