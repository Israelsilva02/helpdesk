package com.helpdesk.api.mapper;

import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.dto.ChamadoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChamadoMapper {

    @Mapping(target = "balcao.id",source = "balcaoId")
    @Mapping(target = "usuario.customerId",source = "usuarioId")
    @Mapping( target = "equipamento.deviceId",source = "equipamentoId")
    Chamado chamadoDTOToChamado(ChamadoDTO chamadoDTO);

    @Mapping(target = "balcaoId",source = "balcao.id")
    @Mapping(target = "usuarioId",source = "usuario.customerId")
    ChamadoDTO chamadoToChamadoDTO(Chamado chamado);
}
