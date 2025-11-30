package com.codingshuttle.om.module2.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    @JsonFormat(pattern = "hh:mm:ss DD-MM-YYYY")
    private LocalDateTime timeStamp ;
    private T data ;
    private ApiError error ;

    public ApiResponse() {
        this.timeStamp = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this() ;
        this.data = data ;
    }

    public ApiResponse(ApiError error) {
        this() ;
        this.error = error ;
    }
}
