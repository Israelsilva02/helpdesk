package com.helpdesk.api.controller;

import com.helpdesk.api.model.dto.EquipamentoDTO;

import com.helpdesk.api.service.EquipamentoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentoController {

    private EquipamentoServiceImpl equipamentoService;

    @GetMapping
    public ResponseEntity<List<EquipamentoDTO>> getAllEquipamentos() {
        List<EquipamentoDTO> equipamentos = equipamentoService.findAll();
        return ResponseEntity.ok(equipamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipamentoDTO> getEquipamentoById(@PathVariable("id") Long id) {
        Optional<EquipamentoDTO> equipamento = equipamentoService.findById(id);
        return equipamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EquipamentoDTO> createEquipamento(@RequestBody EquipamentoDTO equipamentoDTO) {
        EquipamentoDTO savedEquipamentoDTO = equipamentoService.save(equipamentoDTO);
        return ResponseEntity.ok(savedEquipamentoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipamento(@PathVariable("id") Long id) {
        equipamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
