package com.helpdesk.api.controller;

import com.helpdesk.api.model.dto.EquipamentoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipamentos")
public interface EquipamentoController {

    @GetMapping
    ResponseEntity<List<EquipamentoDTO>> findAll();

    @GetMapping("/{id}")
    ResponseEntity<EquipamentoDTO> findById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<EquipamentoDTO> save(@RequestBody EquipamentoDTO equipamentoDTO);

    @PutMapping("/{id}")
    ResponseEntity<EquipamentoDTO> update(@PathVariable("id") Long id, @RequestBody EquipamentoDTO equipamentoDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id);
}
