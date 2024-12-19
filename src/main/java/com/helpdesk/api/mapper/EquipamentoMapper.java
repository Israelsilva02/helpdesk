package com.helpdesk.api.mapper;

import com.helpdesk.api.model.Equipamento;
import com.helpdesk.api.model.dto.EquipamentoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipamentoMapper {

    EquipamentoDTO equipamentoToEquipamentoDTO(Equipamento equipamento);

    Equipamento equipamentoDTOToEquipamento(EquipamentoDTO equipamentoDTO);
}
