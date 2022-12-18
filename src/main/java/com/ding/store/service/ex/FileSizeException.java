package com.ding.store.service.ex;

/**
 * 文件体积异常
 */
public class FileSizeException extends UploadServiceException{
    private static final long serialVersionUID = 8698469294405297383L;

    public FileSizeException() {
    }

    public FileSizeException(String message) {
        super(message);
    }

    public FileSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeException(Throwable cause) {
        super(cause);
    }

    public FileSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
