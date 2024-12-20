package com.helpdesk.api.model.dto;


import java.util.List;

public record BalcaoDTO(Long id,
                        AtendenteBalcaoDTO atendente,
                        List<Long> chamadoIds) {



}
