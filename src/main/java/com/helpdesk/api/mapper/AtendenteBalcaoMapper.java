package com.helpdesk.api.mapper;

import com.helpdesk.api.model.AtendenteBalcao;
import com.helpdesk.api.model.dto.AtendenteBalcaoDTO;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper(componentModel = "spring")
public interface AtendenteBalcaoMapper {
    @Qualifier
    AtendenteBalcaoDTO atendenteBalcaoToAtendenteBalcaoDTO(AtendenteBalcao atendenteBalcao);

    @Qualifier
    AtendenteBalcao atendenteBalcaoDTOToAtendenteBalcao(AtendenteBalcaoDTO atendenteBalcaoDTO);
}
