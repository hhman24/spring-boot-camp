package com.java.spring_boot_camp.common.exceptions;

import com.java.spring_boot_camp.common.enums.ErrorCode;
import lombok.Getter;

@Getter
public class AppException extends Exception {
    private final ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
