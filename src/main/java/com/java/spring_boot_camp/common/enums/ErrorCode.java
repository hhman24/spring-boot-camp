package com.java.spring_boot_camp.common.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter()
public enum ErrorCode {

    BAD_REQUEST(4000, "bad_request", HttpStatus.BAD_REQUEST),
    USER_EXISTS(1001, "user_exists", HttpStatus.BAD_REQUEST),
    INTERNAL(5001, "internal_error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(4001, "forbidden", HttpStatus.FORBIDDEN);

    private final int code;
    private final String message;
    private HttpStatusCode status;

    ErrorCode(int code, String message, HttpStatusCode status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
