package com.helpdesk.api.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpdesk.api.model.dto.ChamadoDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.amqp.core.AmqpTemplate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class ChamadoRequestProducerTest {

    @Mock
    private AmqpTemplate amqpTemplate;

    @InjectMocks
    private ChamadoRequestProducer chamadoRequestProducer;

    private ChamadoDTO chamadoDTO;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        objectMapper = new ObjectMapper();

        chamadoDTO = new ChamadoDTO();
        chamadoDTO.setId(1L);
    }

    @Test
    public void testIntegrar() throws JsonProcessingException {

        String expectedMessage = objectMapper.writeValueAsString(chamadoDTO);

        chamadoRequestProducer.integrar(chamadoDTO);

        verify(amqpTemplate).convertAndSend(
                eq("chamado-request-exchange"),
                eq("chamado-request-rout-key"),
                eq(expectedMessage)
        );
    }
}
