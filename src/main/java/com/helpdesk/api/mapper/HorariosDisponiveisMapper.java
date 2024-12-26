package com.helpdesk.api.mapper;

import com.helpdesk.api.model.HorariosDisponiveis;
import com.helpdesk.api.model.dto.HorariosDisponiveisDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = {ChamadoMapper.class})
public interface HorariosDisponiveisMapper {


    HorariosDisponiveisDTO toDTO(HorariosDisponiveis horariosDisponiveis);

    List<HorariosDisponiveis> toEntityList(List<HorariosDisponiveisDTO> dtos);

    HorariosDisponiveis toEntity(HorariosDisponiveisDTO dto);

    List<HorariosDisponiveisDTO> toDTOList(List<HorariosDisponiveis> dtos);
}
