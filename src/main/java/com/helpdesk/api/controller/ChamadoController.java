package com.helpdesk.api.controller;

import com.helpdesk.api.model.dto.ChamadoDTO;
import com.helpdesk.api.model.EstadoChamado;
import com.helpdesk.api.service.ChamadoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chamados")
public class ChamadoController {

    private final ChamadoServiceImpl chamadoServiceImpl;

    @PostMapping
    public ResponseEntity<ChamadoDTO> createChamado(@Valid @RequestBody ChamadoDTO chamadoDTO) {
        ChamadoDTO createdChamado = chamadoServiceImpl.createChamado(chamadoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChamado);
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> getAllChamados() {
        List<ChamadoDTO> chamados = chamadoServiceImpl.getAllChamados();
        return ResponseEntity.ok(chamados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> getChamadoById(@PathVariable("id") Long id) {
        return chamadoServiceImpl.getChamadoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ChamadoDTO>> getChamadosPorEstado(@PathVariable EstadoChamado estado) {
        List<ChamadoDTO> chamados = chamadoServiceImpl.getChamadosPorEstado(estado);
        return ResponseEntity.ok(chamados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChamadoDTO> updateChamado(@PathVariable("id") Long id, @Valid @RequestBody ChamadoDTO chamadoDTO) {
        return chamadoServiceImpl.updateChamado(id, chamadoDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChamado(@PathVariable("id") Long id) {
        chamadoServiceImpl.deleteChamado(id);
        return ResponseEntity.noContent().build();
    }
}
