package com.helpdesk.api.exception.handler;

import com.helpdesk.api.exception.*;

import com.helpdesk.api.util.MessageConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiExceptionHandlerTest {

    @InjectMocks
    private ApiExceptionHandler apiExceptionHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandleNotFoundException() {
        NotFoundException exception = new NotFoundException("Resource not found");
        ResponseEntity<String> response = apiExceptionHandler.handleNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Resource not found", response.getBody());
    }

    @Test
    public void testHandleSQLException() {
        SQLException exception = new SQLException("SQL error occurred");
        ResponseEntity<String> response = apiExceptionHandler.handleSQLException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("SQL error occurred", response.getBody());
    }

    @Test
    public void testHandleValidationException() {
        ValidationException exception = new ValidationException("Validation failed");
        ResponseEntity<String> response = apiExceptionHandler.handleValidationException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Validation failed", response.getBody());
    }

    @Test
    public void testHandleBalcaoException() {
        BalcaoException exception = new BalcaoException("Balcao error");
        ResponseEntity<String> response = apiExceptionHandler.handleBalcaoException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Balcao error", response.getBody());
    }

    @Test
    public void testHandleChamadoException() {
        ChamadoException exception = new ChamadoException("Chamado error");
        ResponseEntity<String> response = apiExceptionHandler.handleChamadoException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Chamado error", response.getBody());
    }

    @Test
    public void testHandleUsuarioException() {
        UsuarioException exception = new UsuarioException("Usuario error");
        ResponseEntity<String> response = apiExceptionHandler.handleUsuarioException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Usuario error", response.getBody());
    }

    @Test
    public void testHandleGenericException() {
        Exception exception = new Exception("Generic error");
        ResponseEntity<String> response = apiExceptionHandler.handleGenericException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(MessageConstants.OCORREU_UM_ERRO_INESPERADO + "Generic error", response.getBody());
    }
}
