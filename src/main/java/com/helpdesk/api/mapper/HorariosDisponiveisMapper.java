package com.helpdesk.api.mapper;

import com.helpdesk.api.model.AtendenteBalcao;
import com.helpdesk.api.model.HorariosDisponiveis;
import com.helpdesk.api.model.dto.HorariosDisponiveisDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HorariosDisponiveisMapper {

    public static List<HorariosDisponiveisDTO> toDtoHorariosDisponiveis(List<HorariosDisponiveis> horariosDisponiveis) {
        return horariosDisponiveis.stream()
                .map(HorariosDisponiveisMapper::toDtoHorariosDisponiveisDto)
                .collect(Collectors.toList());
    }

    public static HorariosDisponiveisDTO toDtoHorariosDisponiveisDto(HorariosDisponiveis horariosDisponiveis) {
        if (Objects.nonNull(horariosDisponiveis)) {
            return HorariosDisponiveisDTO.builder()
                    .idAtendente(horariosDisponiveis.getAtendenteBalcao().getId())
                    .horariosDisponiveis(horariosDisponiveis.getHorariosDisponiveis())
                    .status(horariosDisponiveis.isStatus())
                    .build();
        }
        return null;
    }

    public static HorariosDisponiveis toEntityHorariosDisponiveis(HorariosDisponiveisDTO horariosDisponiveisDTO) {
        AtendenteBalcao atendenteBalcao = AtendenteBalcao.builder().id(horariosDisponiveisDTO.getIdAtendente()).build();

        return HorariosDisponiveis.builder()
                .atendenteBalcao(atendenteBalcao)
                .horariosDisponiveis(horariosDisponiveisDTO.getHorariosDisponiveis())
                .build();
    }
}
