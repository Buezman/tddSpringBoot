package com.buezman.testdemo.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.ACCEPTED)
    protected BaseResponse<Object> handleCustomExceptions(CustomException e) {
        return BaseResponse.builder()
                .status(e.getStatus())
                .message(e.getMessage())
                .build();
    }
}
