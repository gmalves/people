package com.masson.people.business.exception.response;

import java.util.List;

public class ErrorResponse {

    private final String message;
    private final int code;
    private final List<ErrorObject> errors;

    public ErrorResponse(String message, int code, List<ErrorObject> errors) {
        this.message = message;
        this.code = code;
        this.errors = errors;
    }
}
