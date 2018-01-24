package com.buaa.CliDB.exception.advice;

import com.buaa.CliDB.exception.BaseException;
import com.buaa.CliDB.exception.NotFoundException;
import com.buaa.CliDB.response.ResponseBuilder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseAdvice {

    @ExceptionHandler(BaseException.class)
    public ResponseBuilder BaseAdviceHandler(BaseException e) {
        return new ResponseBuilder(e);
    }

}
