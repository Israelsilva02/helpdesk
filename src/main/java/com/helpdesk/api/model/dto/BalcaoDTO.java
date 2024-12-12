package com.helpdesk.api.model.dto;

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


    private Long idAtendente;
    private List<Long> chamadoIds;

}