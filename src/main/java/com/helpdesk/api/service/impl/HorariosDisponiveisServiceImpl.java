package com.helpdesk.api.service.impl;

import com.helpdesk.api.exception.HorariosDisponiveisException;
import com.helpdesk.api.model.HorariosDisponiveis;
import com.helpdesk.api.repository.HorariosDisponiveisRepository;
import com.helpdesk.api.service.HorariosDisponiveisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HorariosDisponiveisServiceImpl implements HorariosDisponiveisService {
    private final HorariosDisponiveisRepository horariosDisponiveisRepository;

    @Override
    public List<HorariosDisponiveis> findAll() {
        return horariosDisponiveisRepository.findAll();
    }

    @Override
    public HorariosDisponiveis createHorario(HorariosDisponiveis horariosDisponiveis) {
        if (horariosDisponiveis.getId() != null && horariosDisponiveisRepository.findById(horariosDisponiveis.getId()).isPresent()) {
            throw new HorariosDisponiveisException("A consulta com id " + horariosDisponiveis.getId() + " já existe!");
        }
        return horariosDisponiveisRepository.save(horariosDisponiveis);
    }

}
