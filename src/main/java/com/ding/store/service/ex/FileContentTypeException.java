package com.ding.store.service.ex;

/**
 * 文件类型异常
 */
public class FileContentTypeException extends UploadServiceException{
    private static final long serialVersionUID = -2033612515037119729L;

    public FileContentTypeException() {
    }

    public FileContentTypeException(String message) {
        super(message);
    }

    public FileContentTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileContentTypeException(Throwable cause) {
        super(cause);
    }

    public FileContentTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
