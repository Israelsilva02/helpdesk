package com.helpdesk.api.mapper;


import com.helpdesk.api.model.*;
import com.helpdesk.api.model.dto.ChamadoDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChamadoMapper {

    public static List<ChamadoDTO> toDtoChamado(List<Chamado> chamados) {
        return chamados.stream()
                .map(chamado -> toDtoChamadoDto(chamado))
                .collect(Collectors.toList());
    }

    public static ChamadoDTO toDtoChamadoDto(Chamado chamado) {
        if (Objects.nonNull(chamado)) {
            return ChamadoDTO.builder()
                    .balcaoId(chamado.getBalcao().getId())
                    .usuarioId(chamado.getUsuario().getCustomerId())
                    .equipamentoId(chamado.getEquipamento().getDeviceId())
                    .dataChamado(chamado.getDataChamado())
                    .dataResolucao(chamado.getDataResolucao())
                    .estadoChamado(chamado.getEstadoChamado())
                    .motivoChamado(chamado.getMotivoChamado())
                    .build();
        }
        return null;
    }
    public static Chamado toEntityChamado(ChamadoDTO chamadoDTO) {
        Balcao balcao = Balcao.builder().id(chamadoDTO.getBalcaoId()).build();
        Usuario usuario= Usuario.builder().customerId(chamadoDTO.getUsuarioId()).build();
        Equipamento equipamento = Equipamento.builder().deviceId(chamadoDTO.getEquipamentoId()).build();

        return Chamado.builder()
                .balcao(balcao)
                .usuario(usuario)
                .equipamento(equipamento)
                .dataChamado(chamadoDTO.getDataChamado())
                .dataResolucao(chamadoDTO.getDataResolucao())
                .estadoChamado(chamadoDTO.getEstadoChamado())
                .motivoChamado(chamadoDTO.getMotivoChamado())
                .build();


    }
}
