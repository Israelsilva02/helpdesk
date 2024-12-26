package com.helpdesk.api.exception;

public class AtendenteBalcaoException extends RuntimeException {
    public AtendenteBalcaoException(String message) {
    }
    public AtendenteBalcaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
