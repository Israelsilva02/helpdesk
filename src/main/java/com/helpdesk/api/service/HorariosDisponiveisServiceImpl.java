package com.helpdesk.api.service;

import com.helpdesk.api.exception.HorariosDisponiveisException;
import com.helpdesk.api.mapper.HorariosDisponiveisMappeer;
import com.helpdesk.api.model.HorariosDisponiveis;
import com.helpdesk.api.model.dto.HorariosDisponiveisDTO;
import com.helpdesk.api.repository.HorariosDisponiveisRepository;
import com.helpdesk.api.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.helpdesk.api.mapper.HorariosDisponiveisMappeer.*;
import static com.helpdesk.api.mapper.HorariosDisponiveisMappeer.toDtoHorariosDisponiveisDto;
import static com.helpdesk.api.mapper.HorariosDisponiveisMappeer.toEntityHorariosDisponiveis;

@Service
@RequiredArgsConstructor
public class HorariosDisponiveisServiceImpl {
    private final HorariosDisponiveisRepository horariosDisponiveisRepository;


    public List<HorariosDisponiveisDTO> findAll() {
        List<HorariosDisponiveis> horariosDisponiveis = horariosDisponiveisRepository.findAll();
        return toDtoHorariosDisponiveis(horariosDisponiveis);
    }

    public HorariosDisponiveisDTO createHorario(HorariosDisponiveisDTO horariosDisponiveisDTO) {
        HorariosDisponiveis horariosDisponiveis = toEntityHorariosDisponiveis(horariosDisponiveisDTO);
        if (horariosDisponiveisDTO.getIdAtendenteBalcao() != null && horariosDisponiveisRepository.findById(horariosDisponiveis.getId()).isEmpty()) {
            HorariosDisponiveis saveHorariosDiponiveis = horariosDisponiveisRepository.save(horariosDisponiveis);
            return toDtoHorariosDisponiveisDto(saveHorariosDiponiveis);
        }

        throw new HorariosDisponiveisException(MessageConstants.O_ATENDENTE_COMN_O_ID_CORRESPONDENTE_JA_EXISTE + horariosDisponiveis.getAtendenteBalcao());
    }
}
