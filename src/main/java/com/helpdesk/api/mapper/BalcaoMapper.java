package com.helpdesk.api.mapper;

import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.model.dto.BalcaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BalcaoMapper {

    @Mapping(source = "atendente_id", target = "atendente.id")
    @Mapping(target = "id", ignore = true)
    Balcao balcaoDTOToBalcao(BalcaoDTO balcaoDTO);

    @Mapping(source = "atendente.id", target = "atendente_id")
    @Mapping(target = "id", ignore = true)
    BalcaoDTO balcaoToBalcaoDTO(Balcao balcao);


}
