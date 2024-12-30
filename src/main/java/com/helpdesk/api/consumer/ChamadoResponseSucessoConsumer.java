package com.helpdesk.api.consumer;

import com.helpdesk.api.service.ChamadoServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ChamadoResponseSucessoConsumer {
    @Autowired
    private ChamadoServiceImpl chamadoService;

    @RabbitListener(queues = {"chamado-response-sucesso-queue"})
    public void receive(@Payload Message message){
        String payload = String.valueOf(message.getPayload());
        chamadoService.sucessoChamado(payload);
    }
}
