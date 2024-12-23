package com.helpdesk.api.mapper;

import com.helpdesk.api.model.AtendenteBalcao;
import com.helpdesk.api.model.dto.AtendenteBalcaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {BalcaoMapper.class})
public interface AtendenteBalcaoMapper {

    AtendenteBalcaoMapper INSTANCE = Mappers.getMapper(AtendenteBalcaoMapper.class);

    AtendenteBalcaoDTO toDTO(AtendenteBalcao atendenteBalcao);
    List<AtendenteBalcaoDTO> toDTOList(List<AtendenteBalcao> atendenteBalcaos);

    AtendenteBalcao toEntity(AtendenteBalcaoDTO dto);
    List<AtendenteBalcao>toEntityList(List<AtendenteBalcaoDTO> dtos);
}
