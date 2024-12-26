package com.helpdesk.api.mapper;

import com.helpdesk.api.enums.EstadoChamado;
import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.dto.ChamadoDTO;
import com.helpdesk.api.model.dto.VisualizarBalcaoDTO;
import com.helpdesk.api.model.dto.VisualizarChamadoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", imports = {EstadoChamado.class})
public interface ChamadoMapper {
    @Mapping(target = "balcao", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "equipamento", ignore = true)
    ChamadoDTO toDTO(Chamado chamado);

    List<ChamadoDTO> toDTOList(List<Chamado> chamados);

    @Mapping(target = "balcao", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "equipamento", ignore = true)
    @Mapping(target = "estadoChamado", expression = "java(EstadoChamado.ABERTO)")
    Chamado toEntity(ChamadoDTO chamadoDTO);

    List<Chamado> toEntityList(List<ChamadoDTO> chamadoDTOS);

    @Mapping(target = "balcao", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "equipamento", ignore = true)
    void toUpdate(@MappingTarget Chamado chamado, ChamadoDTO chamadoDTO);

    @Mapping(target = "id", source = "chamado.id")
    @Mapping(target = "dataChamado", source = "chamado.dataChamado")
    @Mapping(target = "dataResolucao", source = "chamado.dataResolucao")
    @Mapping(target = "motivoChamado", source = "chamado.motivoChamado")
    @Mapping(target = "estadoChamado", source = "chamado.estadoChamado")
    @Mapping(target = "balcao.id", source = "visualizarBalcaoDTO.id")
    @Mapping(target = "balcao.atendente", source = "visualizarBalcaoDTO.atendente")
    VisualizarChamadoDTO toDTOVisualizar(Chamado chamado, VisualizarBalcaoDTO visualizarBalcaoDTO);
}
