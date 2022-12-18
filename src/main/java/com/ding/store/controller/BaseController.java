package com.ding.store.controller;

import com.ding.store.service.ex.*;
import com.ding.store.util.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

public abstract class BaseController {




    public final Integer getUidFromSession(HttpSession session){
        return (Integer) session.getAttribute("uid");
    }

    public final String getUsernameFromSession(HttpSession session){
        return (String) session.getAttribute("username");
    }

    /*
     * 统一处理异常
     * */
    @ExceptionHandler({ServiceException.class, UploadServiceException.class})
    @ResponseBody
    public Result handleException(Throwable e){
        if(e instanceof UsernameDuplicationException){
            //用户名冲突
          return Result.fail(e.getMessage(),400);
        }else if(e instanceof UserNotFoundException) {
            //401-插入数据异常
            return Result.fail(e.getMessage(),401);
        }else if(e instanceof PasswordNotMatchException) {
            //402-验证密码失败
            return Result.fail(e.getMessage(),402);
        }else if(e instanceof AddressNotFoundException) {
            //403-收货地址数据不存在的异常
            return Result.fail(e.getMessage(),403);
        }else if(e instanceof CartNotFoundException) {
            //404-添加数据不存在的异常
            return Result.fail(e.getMessage(),404);
        }else if(e instanceof AccessDeniedException) {
            //404-拒绝访问，可能权限不足，或数据归属有异
            return Result.fail(e.getMessage(),404);
        }else if(e instanceof InsertException) {
            //500-插入数据异常
            return Result.fail(e.getMessage(),500);
        }else if(e instanceof UpdateException) {
            //501-修改密码失败
            return Result.fail(e.getMessage(),501);
        }else if(e instanceof DeleteException) {
            //502-删除收货地址失败
            return Result.fail(e.getMessage(),502);
        }else if(e instanceof FileEmptyException) {
            //600-上传文件时没有选择文件或选中
            return Result.fail(e.getMessage(),600);
        }else if(e instanceof FileSizeException) {
            //601-上传文件大小错误
            return Result.fail(e.getMessage(),601);
        }else if(e instanceof FileContentTypeException) {
            //602-上传文件类型错误
            return Result.fail(e.getMessage(),602);
        }else if(e instanceof FileIllegalStateException) {
            //603-上传头像错误！存储头像文件时状态异常！
            return Result.fail(e.getMessage(),603);
        }else if(e instanceof FileIOException) {
            //604-上传头像错误！读写文件时出现错误！
            return Result.fail(e.getMessage(),604);
        }
      return Result.fail("异常",444);
    }









}




