package com.helpdesk.api.controller;

import com.helpdesk.api.model.dto.EquipamentoDTO;
import com.helpdesk.api.service.EquipamentoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/equipamentos")
public class EquipamentoController {

    private final EquipamentoServiceImpl equipamentoServiceImpl;

    @GetMapping
    public ResponseEntity<List<EquipamentoDTO>> findAll() {
        return ResponseEntity.ok(equipamentoServiceImpl.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipamentoDTO> findById(@PathVariable("id") Long id) {
        return equipamentoServiceImpl.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<EquipamentoDTO> save(@RequestBody EquipamentoDTO equipamentoDTO) {
        EquipamentoDTO equipamentoDTOnovo = equipamentoServiceImpl.save(equipamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipamentoDTOnovo);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EquipamentoDTO> update(@PathVariable("id") Long id, @RequestBody EquipamentoDTO equipamentoDTO) {
        return equipamentoServiceImpl.update(id,equipamentoDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        equipamentoServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}
