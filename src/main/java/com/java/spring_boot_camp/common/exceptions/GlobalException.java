package com.java.spring_boot_camp.common.exceptions;

import com.java.spring_boot_camp.common.dtos.ApiResponse;
import com.java.spring_boot_camp.common.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingException(Exception exception) {
        ApiResponse res = new ApiResponse();

        res.setCode(ErrorCode.INTERNAL.getCode());
        res.setMessage(ErrorCode.INTERNAL.getMessage());

        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception) {
        ApiResponse res = new ApiResponse();

        res.setCode(1001);
        res.setMessage(exception.getMessage());

        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
        ApiResponse res = new ApiResponse();

        res.setCode(exception.getErrorCode().getCode());
        res.setMessage(exception.getErrorCode().getMessage());

        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();

        ErrorCode errorCode = null;
        ApiResponse res = new ApiResponse();


        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (Exception e) {
            errorCode = ErrorCode.INTERNAL;
        }

        res.setCode(errorCode.getCode());
        res.setMessage(errorCode.getMessage());

        return  ResponseEntity.badRequest().body(res);
    }
}
