package com.helpdesk.api.model.dto;

import com.helpdesk.api.model.AtendenteBalcao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalcaoDTO {

    private Long id;
    private Long atendente;
    private List<Long> chamadoIds;

}
