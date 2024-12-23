package com.helpdesk.api.mapper;

import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.model.dto.BalcaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {AtendenteBalcaoMapper.class})
public interface BalcaoMapper {

    BalcaoMapper INSTANCE = Mappers.getMapper(BalcaoMapper.class);

    BalcaoDTO toDTO(Balcao balcao);

    List<Balcao> toEntityList(List<BalcaoDTO> balcaos);

    Balcao toEntity(BalcaoDTO balcaoDTO);

    List<BalcaoDTO> toDTOList(List<Balcao> balcaos);

}
