package com.helpdesk.api.mapper;

import com.helpdesk.api.model.Equipamento;
import com.helpdesk.api.model.dto.EquipamentoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EquipamentoMapper {
    @Mapping(target = "id", ignore = true)
    EquipamentoDTO equipamentoToEquipamentoDTO(Equipamento equipamento);

    Equipamento equipamentoDTOToEquipamento(EquipamentoDTO equipamentoDTO);
}
