package com.helpdesk.api.mapper;

import com.helpdesk.api.model.Equipamento;
import com.helpdesk.api.model.dto.EquipamentoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EquipamentoMapper {

    EquipamentoDTO toDTO(Equipamento equipamento);
    List<EquipamentoDTO> toDTOList(List<Equipamento> equipamento);

    Equipamento toModel(EquipamentoDTO equipamentoDTO);
    List<Equipamento> toEntity(List<EquipamentoDTO> equipamentoDTO);

    void toUpdate(@MappingTarget Equipamento equipamento, EquipamentoDTO equipamentoDTO);
}
