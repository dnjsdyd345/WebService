package com.spring.blogservice.error;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BusinessException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    public BusinessException(ErrorCode errorCode){
        this.httpStatus = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public BusinessException(ErrorCode errorCode, String message){
        this.httpStatus = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

}
