package com.helpdesk.api.model.dto;

import com.helpdesk.api.enums.EstadoChamado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisualizarChamadoDTO {
    private Long id;
    private LocalDateTime dataChamado;
    private LocalDateTime dataResolucao;
    private String motivoChamado;
    private EstadoChamado estadoChamado;
    private VisualizarBalcaoDTO balcao;
}
