package com.helpdesk.api.exception;

public class BalcaoException extends Exception {
    public BalcaoException(String message) {
        super(message);
    }
    public BalcaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
