package com.victorku.main.web.model;


import com.victorku.main.exceptions.ApplicationErrorTypes;

public class ErrorResponseBody {

    private int code;
    private String message;

    public ErrorResponseBody(ApplicationErrorTypes message) {
        this.code = message.getCode();
        this.message = message.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
