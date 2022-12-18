package com.ding.store.service.ex;

/**
 * 购物车没有i发现
 */
public class CartNotFoundException extends ServiceException {
    private static final long serialVersionUID = 1251566774565496729L;

    public CartNotFoundException() {
    }

    public CartNotFoundException(String message) {
        super(message);
    }

    public CartNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartNotFoundException(Throwable cause) {
        super(cause);
    }

    public CartNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
