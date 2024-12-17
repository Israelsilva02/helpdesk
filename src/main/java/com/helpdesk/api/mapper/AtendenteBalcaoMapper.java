package com.helpdesk.api.mapper;

import com.helpdesk.api.model.AtendenteBalcao;
import com.helpdesk.api.model.dto.AtendenteBalcaoDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AtendenteBalcaoMapper {

    public static List<AtendenteBalcaoDTO> toDtoAtendente(List<AtendenteBalcao> atendentes) {
        return atendentes.stream()
                .map(AtendenteBalcaoMapper::toDtoAtendenteDto)
                .collect(Collectors.toList());
    }

    public static AtendenteBalcaoDTO toDtoAtendenteDto(AtendenteBalcao atendente) {
        if (Objects.nonNull(atendente)) {
            return AtendenteBalcaoDTO.builder()
                    .id(atendente.getId())
                    .nome(atendente.getNome())
                    .build();
        }
        return null;
    }

    public static AtendenteBalcao toEntityAtendente(AtendenteBalcaoDTO atendenteDTO) {
        if (Objects.nonNull(atendenteDTO)) {
            return AtendenteBalcao.builder()
                    .id(atendenteDTO.getId())
                    .nome(atendenteDTO.getNome())
                    .build();
        }
        return null;
    }
}
