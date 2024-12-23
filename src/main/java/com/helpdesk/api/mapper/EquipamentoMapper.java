package com.helpdesk.api.mapper;

import com.helpdesk.api.model.Equipamento;
import com.helpdesk.api.model.dto.EquipamentoDTO;
import org.mapstruct.Mapper;


import static org.mapstruct.factory.Mappers.*;

@Mapper
public interface EquipamentoMapper {
    EquipamentoMapper INSTANCE = getMapper(EquipamentoMapper.class);

    EquipamentoDTO toDTO(Equipamento equipamento);

    Equipamento toModel(EquipamentoDTO equipamentoDTO);

}
