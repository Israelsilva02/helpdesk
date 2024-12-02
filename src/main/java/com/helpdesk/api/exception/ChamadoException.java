package com.helpdesk.api.exception;

public class ChamadoException extends RuntimeException {
    public ChamadoException(String message) {
        super(message);
    }

    public ChamadoException(String message, Throwable cause) {
        super(message, cause);
    }
}
