package com.spring.blogservice.error;

import com.spring.blogservice.controller.response.ResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ResponseBuilder.ErrorResponse> handleBusinessException(BusinessException e){
        final ResponseBuilder.ErrorResponse res = ResponseBuilder.ErrorResponse.of(e.getHttpStatus().value(), e.getCode(), e.getMessage());
        return new ResponseEntity<>(res, e.getHttpStatus());
    }
}
