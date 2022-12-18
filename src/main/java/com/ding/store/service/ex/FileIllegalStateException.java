package com.ding.store.service.ex;

/**
 * 文件状态非法异常
 */
public class FileIllegalStateException extends UploadServiceException {
    private static final long serialVersionUID = -8798059833432947394L;

    public FileIllegalStateException() {
    }

    public FileIllegalStateException(String message) {
        super(message);
    }

    public FileIllegalStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileIllegalStateException(Throwable cause) {
        super(cause);
    }

    public FileIllegalStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
