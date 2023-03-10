package com.masson.people.business.exception.response;

public class ErrorObject {

    private final String message;
    private final String field;
    private final Object parameter;

    public ErrorObject(String message, String field, Object parameter) {
        this.message = message;
        this.field = field;
        this.parameter = parameter;
    }
}
