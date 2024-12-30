package com.helpdesk.api.consumer;
import com.helpdesk.api.consumer.ChamadoResponseErroConsumer;
import com.helpdesk.api.service.ChamadoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class ChamadoResponseErroConsumerTest {

    @Mock
    private ChamadoServiceImpl chamadoService;

    @InjectMocks
    private ChamadoResponseErroConsumer chamadoResponseErroConsumer;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void testRecieve() {
        String testPayload = "Erro ao abrir o chamado";
        Message<String> testMessage = MessageBuilder.withPayload(testPayload).build();
        chamadoResponseErroConsumer.recieve(testMessage);

        verify(chamadoService, times(1)).erroChamado(testPayload);
    }
}
