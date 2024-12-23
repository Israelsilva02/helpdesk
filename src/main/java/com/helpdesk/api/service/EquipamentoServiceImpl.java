package com.helpdesk.api.service;

import com.helpdesk.api.exception.EquipamentoException;
import com.helpdesk.api.mapper.EquipamentoMapper;
import com.helpdesk.api.model.Equipamento;
import com.helpdesk.api.model.dto.EquipamentoDTO;
import com.helpdesk.api.repository.EquipamentoRepository;
import com.helpdesk.api.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.helpdesk.api.mapper.EquipamentoMapper.*;

@RequiredArgsConstructor
@Service
public class EquipamentoServiceImpl {
    private final EquipamentoRepository equipamentoRepository;

    public EquipamentoDTO save(EquipamentoDTO equipamentoDTO) {
        Equipamento equipamento = INSTANCE.toModel(equipamentoDTO);
        Equipamento persistedEquipamento = equipamentoRepository.save(equipamento);
        return INSTANCE.toDTO(persistedEquipamento);
    }

    public List<EquipamentoDTO> findAll() {
        List<Equipamento> equipamentos = equipamentoRepository.findAll();
        return equipamentos.stream()
                .map(INSTANCE::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<EquipamentoDTO> findById(Long id) {
        return equipamentoRepository.findById(id)
                .map(INSTANCE::toDTO);
    }

    public Optional<EquipamentoDTO> update(Long id, EquipamentoDTO equipamentoDTO) {
        return equipamentoRepository.findById(id)
                .map(equipamentoAtualizado -> {
                    Equipamento equipamento = INSTANCE.toModel(equipamentoDTO);
                    equipamento.setDeviceId(equipamentoAtualizado.getDeviceId());
                    return INSTANCE.toDTO(equipamentoRepository.save(equipamento));
                });

    }

    public void delete(Long id) {
        equipamentoRepository.deleteById(id);
    }
}
