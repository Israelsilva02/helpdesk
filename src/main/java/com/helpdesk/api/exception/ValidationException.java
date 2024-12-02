package com.helpdesk.api.exception;

public class ValidationException extends Throwable {
    public ValidationException(String message) {
        super(message);
    }
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
