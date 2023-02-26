package com.masson.people.business.exception.response;

public class DefaultError {

    private int code;
    private String message;

    public DefaultError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
