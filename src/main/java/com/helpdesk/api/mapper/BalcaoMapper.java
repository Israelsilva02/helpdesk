package com.helpdesk.api.mapper;

import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.dto.BalcaoDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BalcaoMapper {

    public static List<BalcaoDTO> toDtoBalcao(List<Balcao> balcoes) {
        return balcoes.stream()
                .map(BalcaoMapper::toDtoBalcaoDto)
                .collect(Collectors.toList());
    }

    public static BalcaoDTO toDtoBalcaoDto(Balcao balcao) {
        if (Objects.nonNull(balcao)) {
            return BalcaoDTO.builder()
                    .id(balcao.getId())
                    .atendente(AtendenteBalcaoMapper.toDtoAtendenteDto(balcao.getAtendente()))
                    .build();
        }
        return null;
    }

    public static Balcao toEntityBalcao(BalcaoDTO balcaoDTO) {
        if (Objects.nonNull(balcaoDTO)) {
            Balcao balcao = Balcao.builder()
                    .id(balcaoDTO.getId())
                    .atendente(AtendenteBalcaoMapper.toEntityAtendente(balcaoDTO.getAtendente()))
                    .build();
            return balcao;
        }
        return null;
    }
}
