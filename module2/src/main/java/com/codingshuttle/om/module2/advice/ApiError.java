package com.codingshuttle.om.module2.advice;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@Data
public class ApiError {
    private String message ;
    private List<String> subErrors ;
    private HttpStatus httpStatus ;
}
