package com.ding.store.controller;


import cn.hutool.http.HttpResponse;
import com.ding.store.entity.User;
import com.ding.store.entity.UserDto;
import com.ding.store.mapper.UserMapper;
import com.ding.store.service.UserService;
import com.ding.store.service.ex.FileEmptyException;
import com.ding.store.service.ex.FileSizeException;
import com.ding.store.service.ex.FileUploadException;
import com.ding.store.service.ex.UploadServiceException;
import com.ding.store.util.PassworrdDto;
import com.ding.store.util.Result;
import com.ding.store.util.UserHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController{
    @Resource
    private UserService userService;


    @PostMapping("isLogin")
    public Result isLogin(){
        UserDto userDto = UserHolder.getUser();
        if (userDto==null){
            return Result.fail("未登录",530);
        }
        return Result.ok(userDto);
    }




    @PostMapping("reg")
    public Result reg(@RequestBody User user){
        userService.addUser(user);
        return Result.ok();
    }


    @PostMapping("login")
    public Result login(@RequestBody User user, HttpSession session){
        UserDto userDto = userService.login(user.getUsername(),user.getPassword());
        session.setAttribute("uid",userDto.getUid());
        session.setAttribute("username",userDto.getUsername());
        return Result.ok(userDto);
    }


    @PostMapping("changePassword")
    public Result changePassword(@RequestBody PassworrdDto passworrdDto){
        userService.updatePassword(
                UserHolder.getUser().getUid(), passworrdDto.getOldPassword(), passworrdDto.getNewPassword());
        return Result.ok();
    }



    @PostMapping("changeInfo")
    public Result changeInfo(@RequestBody User user,HttpSession session){
        user.setUid((Integer) session.getAttribute("uid"));
        User userInfo = userService.updateUserInfo(user);

        return Result.ok();
    }


    /** 头像文件大小的上限值(10MB) */
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    /** 允许上传的头像的文件类型 */
    public static final List<String> AVATAR_TYPES = new ArrayList<String>();

    /** 初始化允许上传的头像的文件类型 */
    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }

    @PostMapping("change_avatar")
    public Result changeAvatar(@RequestParam("file") MultipartFile file, HttpSession session) {
        // 判断上传的文件是否为空
        if (file.isEmpty()) {
            // 是：抛出异常
            throw new FileEmptyException("上传的头像文件不允许为空");
        }

        // 判断上传的文件大小是否超出限制值
        if (file.getSize() > AVATAR_MAX_SIZE) { // getSize()：返回文件的大小，以字节为单位
            // 是：抛出异常
            throw new FileSizeException("不允许上传超过" + (AVATAR_MAX_SIZE / 1024) + "KB的头像文件");
        }

        // 判断上传的文件类型是否超出限制
        String contentType = file.getContentType();
        // boolean contains(Object o)：当前列表若包含某元素，返回结果为true；若不包含该元素，返回结果为false
        if (!AVATAR_TYPES.contains(contentType)) {
            // 是：抛出异常
            throw new UploadServiceException("不支持使用该类型的文件作为头像，允许的文件类型：" + AVATAR_TYPES);
        }

        // 获取当前项目的绝对磁盘路径
        String parent = session.getServletContext().getRealPath("upload");
        System.out.println(parent);
        // 保存头像文件的文件夹
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 保存的头像文件的文件名
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        assert originalFilename != null;
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = UUID.randomUUID().toString() + suffix;

        // 创建文件对象，表示保存的头像文件
        File dest = new File(dir, filename);
        // 执行保存头像文件
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // 抛出异常
            throw new FileUploadException("文件状态异常，可能文件已被移动或删除");
        } catch (IOException e) {
            // 抛出异常
            throw new FileUploadException("上传文件时读写错误，请稍后重新尝试");
        }

        // 头像路径
        String avatar = "/upload/" + filename;
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        // 将头像写入到数据库中
        User user = userService.updateAvatar(uid, avatar);
        // 返回成功头像路径
        return Result.ok(avatar);
    }


    @GetMapping("exit")
    public void exit(HttpSession session, HttpServletResponse response){
        session.removeAttribute("uid");

        try {
            response.sendRedirect("/web/login.html");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
