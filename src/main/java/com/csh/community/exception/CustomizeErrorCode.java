package com.csh.community.exception;

public enum CustomizeErrorCode implements  CustomizeErrorCodeInterface{
    USER_NOT_LOGIN(1001,"未登录"),
    CONTENT_IS_NULL(1002,"评论内容为空");


    private int code;
    private  String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
