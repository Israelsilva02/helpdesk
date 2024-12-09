package com.helpdesk.api.mapper;


import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.dto.BalcaoDTO;

import java.util.List;
import java.util.stream.Collectors;

public class BalcaoMapper {

    public static BalcaoDTO toDto(Balcao balcao) {
        if (balcao == null) {
            return null;
        }

        return BalcaoDTO.builder()
                .id(balcao.getId())
                .nomeAtendente(balcao.getNomeAtendente())
                .chamadoIds(balcao.getChamados().stream()
                        .map(Chamado::getId)
                        .collect(Collectors.toList()))
                .build();
    }

    public static Balcao toEntity(BalcaoDTO balcaoDto) {
        if (balcaoDto == null) {
            return null;
        }

        Balcao balcao = new Balcao();
        balcao.setId(balcaoDto.getId());
        balcao.setNomeAtendente(balcaoDto.getNomeAtendente());

        return balcao;
    }

    public static List<BalcaoDTO> toDtoList(List<Balcao> balcoes) {
        return balcoes.stream()
                .map(BalcaoMapper::toDto)
                .collect(Collectors.toList());
    }
}
