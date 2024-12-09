package com.helpdesk.api.mapper;


import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.EstadoChamado;
import com.helpdesk.api.model.Usuario;
import com.helpdesk.api.model.dto.ChamadoDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ChamadoMapper {

    public static ChamadoDTO toDto(Chamado chamado) {
        if (chamado == null) {
            return null;
        }

        return ChamadoDTO.builder()
                .id(chamado.getId())
                .customerId(chamado.getCustomerId())
                .serialNumber(chamado.getSerialNumber())
                .dataChamado(chamado.getDataChamado())
                .dataResolucao(chamado.getDataResolucao())
                .motivoChamado(chamado.getMotivoChamado())
                .estadoChamado(chamado.getEstadoChamado().name())
                .balcaoId(chamado.getBalcao().getId())
                .usuarioId(chamado.getUsuario().getId())
                .build();
    }

    public static Chamado toEntity(ChamadoDTO chamadoDto) {
        if (chamadoDto == null) {
            return null;
        }

        Chamado chamado = new Chamado();
        chamado.setId(chamadoDto.getId());
        chamado.setCustomerId(chamadoDto.getCustomerId());
        chamado.setSerialNumber(chamadoDto.getSerialNumber());
        chamado.setDataChamado(chamadoDto.getDataChamado());
        chamado.setDataResolucao(chamadoDto.getDataResolucao());
        chamado.setMotivoChamado(chamadoDto.getMotivoChamado());
        chamado.setEstadoChamado(Enum.valueOf(EstadoChamado.class, chamadoDto.getEstadoChamado()));

        chamado.setBalcao(new Balcao(chamadoDto.getBalcaoId(), null, null));
        chamado.setUsuario(new Usuario(chamadoDto.getUsuarioId(), null, null, null));

        return chamado;
    }

    public static List<ChamadoDTO> toDtoList(List<Chamado> chamados) {
        return chamados.stream()
                .map(ChamadoMapper::toDto)
                .collect(Collectors.toList());
    }
}
