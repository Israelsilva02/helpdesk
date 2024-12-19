package com.helpdesk.api.service;

import com.helpdesk.api.mapper.EquipamentoMapper;
import com.helpdesk.api.model.Equipamento;
import com.helpdesk.api.model.dto.EquipamentoDTO;
import com.helpdesk.api.repository.EquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipamentoServiceImpl {

    private final EquipamentoRepository equipamentoRepository;
    private final EquipamentoMapper equipamentoMapper;

    public List<EquipamentoDTO> findAll() {
        List<Equipamento> equipamentos = equipamentoRepository.findAll();
        return equipamentos.stream()
                .map(equipamentoMapper::equipamentoToEquipamentoDTO)
                .collect(Collectors.toList());
    }

    public Optional<EquipamentoDTO> findById(Long id) {
        return equipamentoRepository.findById(id)
                .map(equipamentoMapper::equipamentoToEquipamentoDTO);
    }

    public EquipamentoDTO save(EquipamentoDTO equipamentoDTO) {
        Equipamento equipamento = equipamentoMapper.equipamentoDTOToEquipamento(equipamentoDTO);
        Equipamento savedEquipamento = equipamentoRepository.save(equipamento);
        return equipamentoMapper.equipamentoToEquipamentoDTO(savedEquipamento);
    }

    public void deleteById(Long id) {
        equipamentoRepository.deleteById(id);
    }
}
