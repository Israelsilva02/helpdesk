package com.helpdesk.api.model.dto;


import com.helpdesk.api.model.enums.EstadoChamado;

import java.time.LocalDateTime;

public record ChamadoDTO(Long customerId,
                         LocalDateTime dataChamado,
                         LocalDateTime dataResolucao,
                         EstadoChamado estadoChamado,
                         String motivoChamado,
                         Long equipamentoId,
                         Long usuarioId,
                         Long balcaoId
                         ) {
}
