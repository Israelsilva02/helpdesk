package com.helpdesk.api.controller;

import com.helpdesk.api.model.dto.ChamadoDTO;
import com.helpdesk.api.enums.EstadoChamado;
import com.helpdesk.api.model.dto.VisualizarChamadoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/chamados")
public interface ChamadoController {

    @PostMapping
    ResponseEntity<VisualizarChamadoDTO> createChamado(@Valid @RequestBody ChamadoDTO chamadoDTO);

    @GetMapping
    ResponseEntity<List<ChamadoDTO>> getAllChamados();

    @GetMapping("/{id}")
    ResponseEntity<ChamadoDTO> getChamadoById(@PathVariable("id") Long id);

    @GetMapping("/estado/{estado}")
    ResponseEntity<List<ChamadoDTO>> getChamadosPorEstado(@PathVariable("estado") EstadoChamado estado);

    @PutMapping("/{id}")
    ResponseEntity<ChamadoDTO> updateChamado(@PathVariable("id") Long id, @Valid @RequestBody ChamadoDTO chamadoDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteChamado(@PathVariable("id") Long id);
}
