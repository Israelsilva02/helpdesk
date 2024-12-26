package com.helpdesk.api.controller.impl;

import com.helpdesk.api.controller.EquipamentoController;
import com.helpdesk.api.model.dto.EquipamentoDTO;
import com.helpdesk.api.service.EquipamentoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EquipamentoControllerImpl implements EquipamentoController {

    private final EquipamentoServiceImpl equipamentoServiceImpl;

    @Override
    public ResponseEntity<List<EquipamentoDTO>> findAll() {
        return ResponseEntity.ok(equipamentoServiceImpl.findAll());
    }

    @Override
    public ResponseEntity<EquipamentoDTO> findById(Long id) {
        EquipamentoDTO equipamentoDTO = equipamentoServiceImpl.findById(id);
        return ResponseEntity.ok(equipamentoDTO);
    }

    @Override
    public ResponseEntity<EquipamentoDTO> save(EquipamentoDTO equipamentoDTO) {
        EquipamentoDTO createdEquipamento = equipamentoServiceImpl.save(equipamentoDTO);
        return ResponseEntity.ok(createdEquipamento);
    }

    @Override
    public ResponseEntity<EquipamentoDTO> update(Long id, EquipamentoDTO equipamentoDTO) {
       EquipamentoDTO updatedEquipamento = equipamentoServiceImpl.update(id, equipamentoDTO);
       return ResponseEntity.ok(updatedEquipamento);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        equipamentoServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}
