package com.helpdesk.api.model.dto;

import com.helpdesk.api.model.AtendenteBalcao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisualizarBalcaoDTO {
    private Long id;
    private String atendente;
}
