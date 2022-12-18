package com.ding.store.service.ex;

/**
 * 文件为空异常
 */
public class FileEmptyException extends UploadServiceException{
    private static final long serialVersionUID = 1560597296663252616L;

    public FileEmptyException() {
    }

    public FileEmptyException(String message) {
        super(message);
    }

    public FileEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileEmptyException(Throwable cause) {
        super(cause);
    }

    public FileEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
