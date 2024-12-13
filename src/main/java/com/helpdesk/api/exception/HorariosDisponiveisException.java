package com.helpdesk.api.exception;

public class HorariosDisponiveisException extends RuntimeException {
    public HorariosDisponiveisException(String menssge) {
        super(menssge);
    }
    public HorariosDisponiveisException(String menssge, Throwable cause) {
        super(menssge, cause);
    }
}
