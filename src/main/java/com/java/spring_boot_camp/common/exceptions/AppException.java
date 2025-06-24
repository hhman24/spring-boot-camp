package com.java.spring_boot_camp.common.exceptions;

import com.java.spring_boot_camp.common.enums.ErrorCode;

public class AppException extends Exception {
    private final ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
