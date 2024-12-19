package com.helpdesk.api.mapper;

import com.helpdesk.api.model.Balcao;

import com.helpdesk.api.model.dto.BalcaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BalcaoMapper {
    @Mapping(source = "chamados", target = "chamadoIds", qualifiedByName = "mapChamadosToIds")
    BalcaoDTO balcaoToBalcaoDTO(Balcao balcao);

    Balcao balcaoDTOToBalcao(BalcaoDTO balcaoDTO);


}
