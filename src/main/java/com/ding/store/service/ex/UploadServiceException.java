package com.ding.store.service.ex;

/**
 *上传文件异常
 */
public class UploadServiceException extends RuntimeException{
    private static final long serialVersionUID = 8560982307395130907L;

    public UploadServiceException() {
    }

    public UploadServiceException(String message) {
        super(message);
    }

    public UploadServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UploadServiceException(Throwable cause) {
        super(cause);
    }

    public UploadServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
