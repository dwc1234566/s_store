package com.ding.store.service.ex;

/**
 * 用户名不存在或者已被删除
 */
public class UserNotFoundException extends ServiceException{
    private static final long serialVersionUID = 1515646283883788301L;

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
