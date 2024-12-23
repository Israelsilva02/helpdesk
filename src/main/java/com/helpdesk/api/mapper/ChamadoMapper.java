package com.helpdesk.api.mapper;

import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.dto.ChamadoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChamadoMapper {
    ChamadoMapper INSTANCE = Mappers.getMapper(ChamadoMapper.class);

    ChamadoDTO toDTO(Chamado chamado);

    List<ChamadoDTO> toDTOList(List<Chamado> chamados);

    Chamado toEntity(ChamadoDTO chamadoDTO);

    List<Chamado> toEntityList(List<ChamadoDTO> chamadoDTOS);

}
