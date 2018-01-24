package com.buaa.CliDB.response;


import lombok.Getter;

@Getter
public enum ResponseStatusAndInfos {

    OK(0, "success"), ERROR(100, "error");

    private int status;
    private String msg;

    ResponseStatusAndInfos(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

}
