package com.ding.store.service.ex;

/**
 * 文件io异常
 */
public class FileIOException extends UploadServiceException {
    private static final long serialVersionUID = 6053941250169419463L;

    public FileIOException() {
    }

    public FileIOException(String message) {
        super(message);
    }

    public FileIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileIOException(Throwable cause) {
        super(cause);
    }

    public FileIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
