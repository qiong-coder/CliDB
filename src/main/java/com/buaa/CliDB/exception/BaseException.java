package com.buaa.CliDB.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseException extends RuntimeException {

    private int status;

    public BaseException(int status, String s) {
        super(s);
        this.status = status;
    }

}
