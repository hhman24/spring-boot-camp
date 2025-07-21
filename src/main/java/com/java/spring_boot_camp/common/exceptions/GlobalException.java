package com.java.spring_boot_camp.common.exceptions;

import com.java.spring_boot_camp.common.dtos.ApiResponse;
import com.java.spring_boot_camp.common.enums.ErrorCode;
import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.Objects;

@Slf4j
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

        return ResponseEntity
                .status(exception.getErrorCode().getStatus())
                .body(res);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        ApiResponse res = new ApiResponse();

        res.setCode(errorCode.getCode());
        res.setMessage(errorCode.getMessage());

        return ResponseEntity
                .status(errorCode.getStatus())
                .body(res);
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();

        ErrorCode errorCode = null;
        ApiResponse res = new ApiResponse();
        Map<String, Object> attributes = null;

        try {
            errorCode = ErrorCode.valueOf(enumKey);

            var constraintViolation = exception.getBindingResult()
                    .getAllErrors()
                    .getFirst()
                    .unwrap(ConstraintViolation.class);

            attributes = constraintViolation.getConstraintDescriptor().getAttributes();

            log.info(attributes.toString());

        } catch (Exception e) {
            errorCode = ErrorCode.INTERNAL;
        }

        res.setCode(errorCode.getCode());
        res.setMessage(Objects.nonNull(attributes) ? mapAttribute(errorCode.getMessage(), attributes) : errorCode.getMessage());

        return ResponseEntity.badRequest().body(res);
    }

    private String mapAttribute(String message, Map<String, Object> attributes) {
        String minValue = String.valueOf(attributes.get("min"));

        return message.replace("{" + minValue + "}", minValue);
    }

}
