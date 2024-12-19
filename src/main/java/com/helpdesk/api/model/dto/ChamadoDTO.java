package com.helpdesk.api.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesk.api.model.enums.EstadoChamado;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
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
