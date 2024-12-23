package com.helpdesk.api.service;

import com.helpdesk.api.exception.HorariosDisponiveisException;
import com.helpdesk.api.model.HorariosDisponiveis;
import com.helpdesk.api.model.dto.HorariosDisponiveisDTO;
import com.helpdesk.api.repository.HorariosDisponiveisRepository;
import com.helpdesk.api.util.MessageConstants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.helpdesk.api.mapper.HorariosDisponiveisMapper.*;

@Service
@RequiredArgsConstructor
@Transactional
public class HorariosDisponiveisServiceImpl {
    private final HorariosDisponiveisRepository horariosDisponiveisRepository;

    public List<HorariosDisponiveisDTO> findAll() {
        List<HorariosDisponiveis> horariosDisponiveis = horariosDisponiveisRepository.findAll();
        return INSTANCE.toDTOList(horariosDisponiveis);
    }

    public HorariosDisponiveisDTO createHorario(HorariosDisponiveisDTO horariosDisponiveisDTO) {
        if (horariosDisponiveisDTO.getHorariosDisponiveis() != null) {
            HorariosDisponiveis horariosDisponiveis = INSTANCE.toEntity(horariosDisponiveisDTO);
            if (!horariosDisponiveisRepository.existsById(horariosDisponiveis.getId())) {
                HorariosDisponiveis saveHorariosDiponiveis = horariosDisponiveisRepository.save(horariosDisponiveis);
                return INSTANCE.toDTO(saveHorariosDiponiveis);
            }
        }
        throw new HorariosDisponiveisException(MessageConstants.O_ATENDENTE_COM_O_ID_CORRESPONDENTE_JA_EXISTE + horariosDisponiveisDTO.getHorariosDisponiveis());
    }
}
