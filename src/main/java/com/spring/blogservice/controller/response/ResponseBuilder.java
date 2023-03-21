package com.spring.blogservice.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collection;

/** 단일 , Collection에*/
public class ResponseBuilder {
    public static ResponseEntity<ResponseRoot> build(HttpStatus status) { return new ResponseEntity<>(status); }

    public static <T> ResponseEntity<ResponseRoot> build(T data, HttpStatus status){
        ResponseRoot result = null;
        if(data instanceof Collection) result = new ResponseCollection<>(data, ((Collection<?>) data).size());
        else result = new ResponseObject<>(data);

        return new ResponseEntity<>(result, status);
    }

    public static class ResponseObject<T> implements ResponseRoot{
        @Schema(name = "data", title = "data", description = "데이터")
        private T data;

        public ResponseObject(T data) { this.data = data; }
        public T getData() { return data; }
        public void setData() {this.data = data;}
    }

    public static class ResponseCollection<T> implements ResponseRoot{
        @Schema(name = "data", title = "data", description = "데이터")
        private T data;
        @Schema(title = "데이터 수", description = "데이터 수")
        private int count = 0;
        private ResponseCollection(T datas, int count){
            this.data = datas;
            this.count = count;
        }
        public T getData() {return data;}
        public void setData(T data){this.data = data;}
        public int getCount() {return count;}
        public void setCount(int count){this.count = count;}
    }

    @Getter
    public static class ErrorResponse implements ResponseRoot {
        private final int status;
        private final String code;
        private final String message;

        private ErrorResponse(int status, String code, String message){
            this.status = status;
            this.code = code;
            this.message = message;
        }

        public static ErrorResponse of(int status, String code, String message){
            return new ErrorResponse(
                    status,
                    code,
                    message
            );
        }
    }

}
