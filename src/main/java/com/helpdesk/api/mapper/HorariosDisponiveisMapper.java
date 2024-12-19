package com.helpdesk.api.mapper;

import com.helpdesk.api.model.HorariosDisponiveis;
import com.helpdesk.api.model.dto.HorariosDisponiveisDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HorariosDisponiveisMapper {

    @Mapping(source = "atendenteBalcao.id", target = "idAtendente")
    HorariosDisponiveisDTO horariosDisponiveisToHorariosDisponiveisDTO(HorariosDisponiveis horariosDisponiveis);

    @Mapping(source = "idAtendente", target = "atendenteBalcao.id")
    HorariosDisponiveis horariosDisponiveisDTOToHorariosDisponiveis(HorariosDisponiveisDTO horariosDisponiveisDTO);
}
