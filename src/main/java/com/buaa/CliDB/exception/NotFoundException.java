package com.buaa.CliDB.exception;


public class NotFoundException extends BaseException {

    public NotFoundException(int status, String s) {
        super(status, s);
    }

}
