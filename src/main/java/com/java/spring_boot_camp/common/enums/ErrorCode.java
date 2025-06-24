package com.java.spring_boot_camp.common.enums;

public enum ErrorCode {

    BAD_REQUEST(4003, "bad_request"),
    USER_EXISTS(1001, "user_exists"),
    INTERNAL(5001, "internal_error");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
