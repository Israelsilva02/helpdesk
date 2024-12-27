package com.helpdesk.api.mapper;

import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.model.dto.BalcaoDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


import java.util.List;

@Mapper(componentModel = "spring",uses = {AtendenteBalcaoMapper.class})
public interface BalcaoMapper {
    @Mapping(target = "atendente", source = "atendente.id")
    BalcaoDTO toDTO(Balcao balcao);

    List<Balcao> toEntityList(List<BalcaoDTO> balcaos);

    @Mapping(target = "atendente", ignore = true)
    Balcao toEntity(BalcaoDTO balcaoDTO);

    List<BalcaoDTO> toDTOList(List<Balcao> balcaos);

    @Mapping(target = "atendente", ignore = true)
    void toUpdate(@MappingTarget Balcao balcao, BalcaoDTO balcaoDTO);

//    VisualizarBalcaoDTO toDTOVisualizar(Balcao balcao, VisualizarBalcaoDTO visualizarBalcaoDTO);
}
