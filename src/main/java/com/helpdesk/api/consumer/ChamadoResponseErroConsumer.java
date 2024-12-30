package com.helpdesk.api.consumer;


import com.helpdesk.api.service.ChamadoServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component("ChamadoResponseErroConsumer")
public class ChamadoResponseErroConsumer {
    @Autowired
    private ChamadoServiceImpl chamadoService;

    @RabbitListener(queues = {"chamado-response-erro-queue"})
    public void recieve(@Payload Message message) {
        System.out.println("Messge" + message + " " + LocalDateTime.now());
        String payload = String.valueOf(message.getPayload());
        chamadoService.erroChamado(payload);
    }
}
