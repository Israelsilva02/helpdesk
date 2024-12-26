package com.helpdesk.api.mapper;

import com.helpdesk.api.model.AtendenteBalcao;
import com.helpdesk.api.model.dto.AtendenteBalcaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


import java.util.List;


@Mapper(componentModel = "spring", uses = {BalcaoMapper.class})
public interface AtendenteBalcaoMapper {

    AtendenteBalcaoDTO toDTO(AtendenteBalcao atendenteBalcao);

    List<AtendenteBalcaoDTO> toDTOList(List<AtendenteBalcao> atendenteBalcaos);

    AtendenteBalcao toEntity(AtendenteBalcaoDTO dto);

    List<AtendenteBalcao> toEntityList(List<AtendenteBalcaoDTO> dtos);

    void toUpdate(@MappingTarget AtendenteBalcao atendenteBalcao, AtendenteBalcaoDTO atendenteBalcaoDTO);
}
