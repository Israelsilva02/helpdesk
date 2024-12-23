package com.helpdesk.api.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesk.api.enums.EstadoChamado;
import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.model.Equipamento;
import com.helpdesk.api.model.Usuario;
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

    private Long id;
    private LocalDateTime dataChamado;
    private LocalDateTime dataResolucao;
    private String motivoChamado;
    private EstadoChamado estadoChamado;

    private Balcao balcao;
    private Usuario usuario;
    private Equipamento equipamento;
}
