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


@RequiredArgsConstructor
@Service
public class EquipamentoServiceImpl {
    private final EquipamentoRepository equipamentoRepository;
    private final EquipamentoMapper equipamentoMapper;

    public EquipamentoDTO save(EquipamentoDTO equipamentoDTO) {
        Equipamento equipamento = equipamentoMapper.toModel(equipamentoDTO);
        Equipamento persistedEquipamento = equipamentoRepository.save(equipamento);
        return equipamentoMapper.toDTO(persistedEquipamento);
    }

    public List<EquipamentoDTO> findAll() {
        List<Equipamento> equipamentos = equipamentoRepository.findAll();
        return equipamentoMapper.toDTOList(equipamentos);
    }

    public EquipamentoDTO findById(Long id) {
        Equipamento equipamento = equipamentoRepository.findById(id)
                .orElseThrow(() -> new EquipamentoException(MessageConstants.EQUIPAMENTO_COM_O_ID_NAO_EXISTE + id));
        return equipamentoMapper.toDTO(equipamento);
    }

    public EquipamentoDTO update(Long id, EquipamentoDTO equipamentoDTO) {
        Equipamento equipamento = equipamentoRepository.findById(id)
                .orElseThrow(() -> new EquipamentoException(MessageConstants.EQUIPAMENTO_COM_O_ID_NAO_EXISTE + id));
        equipamentoMapper.toUpdate(equipamento, equipamentoDTO);
        equipamentoRepository.save(equipamento);
        return equipamentoMapper.toDTO(equipamento);

    }

    public void delete(Long id) {
        if (!equipamentoRepository.existsById(id)) {
            throw new RuntimeException(MessageConstants.EQUIPAMENTO_COM_O_ID_NAO_EXISTE);
        }
        equipamentoRepository.deleteById(id);
    }
}
