package com.victorku.main.exceptions;

public enum ApplicationErrorTypes {

    BOOKING_ID_NOT_FOUND(1, "booking with this id is not found");

    private String message;
    private int code;

    ApplicationErrorTypes(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
