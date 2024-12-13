package com.helpdesk.api.mapper;


import com.helpdesk.api.model.AtendenteBalcao;
import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.dto.BalcaoDTO;
import org.springframework.web.bind.annotation.Mapping;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BalcaoMapper {

    public static List<BalcaoDTO> toDtoBalcao(List<Balcao> balcaoList) {
        return balcaoList.stream()
                .map(balcao -> toDtoBalcaoDto(balcao))
                .collect(Collectors.toList());
    }

    public static BalcaoDTO toDtoBalcaoDto(Balcao balcao) {
        if ((Objects.nonNull(balcao))) {
            return BalcaoDTO.builder()
                    .idAtendente(balcao.getAtendente().getId())
                    .build();

        } else {
            return BalcaoDTO.builder().build();
        }
    }

    public static Balcao toEntityBalcao(BalcaoDTO balcaoDTO) {
        AtendenteBalcao atendenteBalcao = AtendenteBalcao.builder().id(balcaoDTO.getIdAtendente()).build();
        return Balcao.builder()
                .atendente(atendenteBalcao)
                .build();
    }
}
