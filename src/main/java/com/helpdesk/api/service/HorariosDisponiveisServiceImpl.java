package com.helpdesk.api.service;

import com.helpdesk.api.exception.HorariosDisponiveisException;
import com.helpdesk.api.model.HorariosDisponiveis;
import com.helpdesk.api.model.dto.HorariosDisponiveisDTO;
import com.helpdesk.api.repository.HorariosDisponiveisRepository;
import com.helpdesk.api.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.helpdesk.api.mapper.HorariosDisponiveisMapper.*;

@Service
@RequiredArgsConstructor
public class HorariosDisponiveisServiceImpl {
    private final HorariosDisponiveisRepository horariosDisponiveisRepository;

    public List<HorariosDisponiveisDTO> findAll() {
        List<HorariosDisponiveis> horariosDisponiveis = horariosDisponiveisRepository.findAll();
        return toDtoHorariosDisponiveis(horariosDisponiveis);
    }

    public HorariosDisponiveisDTO createHorario(HorariosDisponiveisDTO horariosDisponiveisDTO) {
        if (horariosDisponiveisDTO.getIdAtendente() != null) {
            HorariosDisponiveis horariosDisponiveis = toEntityHorariosDisponiveis(horariosDisponiveisDTO);
            if (!horariosDisponiveisRepository.existsById(horariosDisponiveis.getId())) {
                HorariosDisponiveis saveHorariosDiponiveis = horariosDisponiveisRepository.save(horariosDisponiveis);
                return toDtoHorariosDisponiveisDto(saveHorariosDiponiveis);
            }
        }

        throw new HorariosDisponiveisException(MessageConstants.O_ATENDENTE_COM_O_ID_CORRESPONDENTE_JA_EXISTE + horariosDisponiveisDTO.getIdAtendente());
    }
}
