package com.buaa.CliDB.exception;

public class KeyDuplicateException extends BaseException {

    public KeyDuplicateException(int status, String s) {
        super(status, s);
    }
}
