package com.helpdesk.api.producer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpdesk.api.model.dto.ChamadoDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ChamadoRequestProducer")
public class ChamadoRequestProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void integrar(ChamadoDTO chamadoDTO) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                "chamado-request-exchange",
                "chamado-request-rout-key",
                objectMapper.writeValueAsString(chamadoDTO));
    }
}
