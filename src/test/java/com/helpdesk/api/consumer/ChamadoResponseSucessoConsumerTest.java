package com.helpdesk.api.consumer;

import com.helpdesk.api.consumer.ChamadoResponseSucessoConsumer;
import com.helpdesk.api.service.ChamadoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class ChamadoResponseSucessoConsumerTest {

    @Mock
    private ChamadoServiceImpl chamadoService;

    @InjectMocks
    private ChamadoResponseSucessoConsumer chamadoResponseSucessoConsumer;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void testReceive() {
        String testPayload = "Chamado aberto com sucesso";
        Message<String> testMessage = MessageBuilder.withPayload(testPayload).build();

        chamadoResponseSucessoConsumer.receive(testMessage);

        verify(chamadoService, times(1)).sucessoChamado(testPayload);
    }
}
