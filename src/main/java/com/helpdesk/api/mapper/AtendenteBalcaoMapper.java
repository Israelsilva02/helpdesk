package com.helpdesk.api.mapper;

import com.helpdesk.api.model.AtendenteBalcao;
import com.helpdesk.api.model.dto.AtendenteBalcaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper
public interface AtendenteBalcaoMapper {
    @Mapping(source = "nome", target = "nome")
    @Mapping(target = "id", ignore = true)
    AtendenteBalcaoDTO atendenteBalcaoToAtendenteBalcaoDTO(AtendenteBalcao atendenteBalcao);

    @Mapping(source = "nome", target = "nome")
    @Mapping(target = "id", ignore = true)
    AtendenteBalcao atendenteBalcaoDTOToAtendenteBalcao(AtendenteBalcaoDTO atendenteBalcaoDTO);
}
