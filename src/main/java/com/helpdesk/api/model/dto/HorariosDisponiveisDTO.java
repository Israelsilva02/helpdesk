package com.helpdesk.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HorariosDisponiveisDTO {

    private Long idAtendenteBalcao;
    private LocalDateTime horariosDisponiveis;
    private boolean status;
}
