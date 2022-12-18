package com.ding.store.service.ex;

/**
 * 用户名被占用的异常,例如注册时用户名被占用
 */
public class UsernameDuplicationException extends ServiceException {
    private static final long serialVersionUID = 2197244562555865320L;

    public UsernameDuplicationException() {
    }

    public UsernameDuplicationException(String message) {
        super(message);
    }

    public UsernameDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDuplicationException(Throwable cause) {
        super(cause);
    }

    public UsernameDuplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
