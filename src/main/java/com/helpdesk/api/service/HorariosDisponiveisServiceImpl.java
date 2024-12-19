package com.helpdesk.api.service;

import com.helpdesk.api.exception.HorariosDisponiveisException;
import com.helpdesk.api.mapper.HorariosDisponiveisMapper;
import com.helpdesk.api.model.HorariosDisponiveis;
import com.helpdesk.api.model.dto.HorariosDisponiveisDTO;
import com.helpdesk.api.repository.HorariosDisponiveisRepository;
import com.helpdesk.api.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HorariosDisponiveisServiceImpl {
    private final HorariosDisponiveisRepository horariosDisponiveisRepository;
    private final HorariosDisponiveisMapper horariosDisponiveisMapper;

    public List<HorariosDisponiveisDTO> findAll() {
        return horariosDisponiveisRepository.findAll().stream()
                .map(horariosDisponiveisMapper::horariosDisponiveisToHorariosDisponiveisDTO)
                .collect(Collectors.toList());
    }

    public HorariosDisponiveisDTO createHorario(HorariosDisponiveisDTO horariosDisponiveisDTO) {
        HorariosDisponiveis horariosDisponiveis = horariosDisponiveisMapper
                .horariosDisponiveisDTOToHorariosDisponiveis(horariosDisponiveisDTO);

        if (!horariosDisponiveisRepository.existsById(horariosDisponiveis.getId())) {
            HorariosDisponiveis savedHorariosDisponiveis = horariosDisponiveisRepository.save(horariosDisponiveis);
            return horariosDisponiveisMapper.horariosDisponiveisToHorariosDisponiveisDTO(savedHorariosDisponiveis);
        }

        throw new HorariosDisponiveisException(MessageConstants.O_ATENDENTE_COM_O_ID_CORRESPONDENTE_JA_EXISTE + horariosDisponiveisDTO.getIdAtendente());
    }
}
