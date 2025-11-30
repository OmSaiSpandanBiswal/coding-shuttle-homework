package com.codingshuttle.om.module2.advice;

import com.codingshuttle.om.module2.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ApiResponse<?>> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
        ApiError apiError = ApiError
                .builder()
                .message(exception.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .build() ;
        return buildApiErrorAsApiResponse(apiError) ;
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse<?>> methodArgumentNotValidExceptionHandler (MethodArgumentNotValidException exception) {

        List<String> subErrors = exception
                .getAllErrors()
                .stream()
                .map(err -> err.getDefaultMessage())
                .toList() ;

        ApiError apiError = ApiError
                .builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("Input Validation Failed")
                .subErrors(subErrors)
                .build() ;
        return buildApiErrorAsApiResponse(apiError) ;

    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse<?>> internalServerErrorHandler (Exception exception) {
        ApiError apiError = ApiError
                .builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .build() ;
        return buildApiErrorAsApiResponse(apiError) ;
    }

    public ResponseEntity<ApiResponse<?>> buildApiErrorAsApiResponse(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getHttpStatus()) ;
    }

}
