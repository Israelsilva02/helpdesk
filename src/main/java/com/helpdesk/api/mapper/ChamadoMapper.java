package com.helpdesk.api.mapper;

import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.dto.ChamadoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChamadoMapper {

    @Mapping(source = "balcao.id", target = "balcaoId")
    @Mapping(source = "usuario.customerId", target = "usuarioId")
    @Mapping(source = "equipamento.deviceId", target = "equipamentoId")
    ChamadoDTO chamadoToChamadoDTO(Chamado chamado);

    @Mapping(source = "balcaoId", target = "balcao.id")
    @Mapping(source = "usuarioId", target = "usuario.customerId")
    @Mapping(source = "equipamentoId", target = "equipamento.deviceId")
    Chamado chamadoDTOToChamado(ChamadoDTO chamadoDTO);
}
