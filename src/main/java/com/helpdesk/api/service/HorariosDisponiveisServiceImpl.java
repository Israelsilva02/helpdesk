package com.helpdesk.api.service;

import com.helpdesk.api.exception.HorariosDisponiveisException;
import com.helpdesk.api.mapper.HorariosDisponiveisMapper;
import com.helpdesk.api.model.HorariosDisponiveis;
import com.helpdesk.api.model.dto.HorariosDisponiveisDTO;
import com.helpdesk.api.repository.HorariosDisponiveisRepository;
import com.helpdesk.api.util.MessageConstants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class HorariosDisponiveisServiceImpl {
    private final HorariosDisponiveisRepository horariosDisponiveisRepository;
    private final HorariosDisponiveisMapper horariosDisponiveisMapper;

    public List<HorariosDisponiveisDTO> findAll() {
        List<HorariosDisponiveis> horariosDisponiveis = horariosDisponiveisRepository.findAll();
        return horariosDisponiveisMapper.toDTOList(horariosDisponiveis);
    }

    public HorariosDisponiveisDTO createHorario(HorariosDisponiveisDTO horariosDisponiveisDTO) {
        HorariosDisponiveis horariosDisponiveis = horariosDisponiveisMapper.toEntity(horariosDisponiveisDTO);
        HorariosDisponiveis horariosDisponiveisSave = horariosDisponiveisRepository.save(horariosDisponiveis);
        return horariosDisponiveisMapper.toDTO(horariosDisponiveisSave);
    }

}
