package com.helpdesk.api.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesk.api.model.EstadoChamado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChamadoDTO {

    private Long customerId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataChamado;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataResolucao;

    private String motivoChamado;
    private EstadoChamado estadoChamado;

    private Long balcaoId;
    private Long usuarioId;
    private Long equipamentoId;
}
