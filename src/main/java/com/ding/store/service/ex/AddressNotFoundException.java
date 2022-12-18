package com.ding.store.service.ex;

/**
 * 地址查询不到
 */
public class AddressNotFoundException extends ServiceException{
    private static final long serialVersionUID = 4981536419534225401L;

    public AddressNotFoundException() {
    }

    public AddressNotFoundException(String message) {
        super(message);
    }

    public AddressNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressNotFoundException(Throwable cause) {
        super(cause);
    }

    public AddressNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
