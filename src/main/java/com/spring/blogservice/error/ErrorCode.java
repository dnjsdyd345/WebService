package com.spring.blogservice.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    NO_CONTENT(HttpStatus.NO_CONTENT, "NO_CONTENT", "필수값 정보가 누락 되었습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
