package com.helpdesk.api.exception;

public class EquipamentoException extends RuntimeException {
    public EquipamentoException(String msg) {
    }
    public EquipamentoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
