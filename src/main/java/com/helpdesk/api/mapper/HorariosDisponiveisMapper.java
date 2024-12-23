package com.helpdesk.api.mapper;

import com.helpdesk.api.model.HorariosDisponiveis;
import com.helpdesk.api.model.dto.HorariosDisponiveisDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ChamadoMapper.class})
public interface HorariosDisponiveisMapper {

    HorariosDisponiveisMapper INSTANCE = Mappers.getMapper(HorariosDisponiveisMapper.class);

    HorariosDisponiveisDTO toDTO(HorariosDisponiveis horariosDisponiveis);

    List<HorariosDisponiveis> toEntityList(List<HorariosDisponiveisDTO> dtos);

    HorariosDisponiveis toEntity(HorariosDisponiveisDTO dto);

    List<HorariosDisponiveisDTO> toDTOList(List<HorariosDisponiveis> dtos);
}
