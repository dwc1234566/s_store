package com.ding.store.service.ex;

/**
 * 上传文件是异常的基类
 */
public class FileUploadException extends UploadServiceException{
    private static final long serialVersionUID = 934854109964140401L;

    public FileUploadException() {
    }

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadException(Throwable cause) {
        super(cause);
    }

    public FileUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
