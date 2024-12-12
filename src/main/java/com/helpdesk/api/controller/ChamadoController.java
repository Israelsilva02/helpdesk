package com.helpdesk.api.controller;

import com.helpdesk.api.model.dto.ChamadoDTO;

import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.EstadoChamado;
import com.helpdesk.api.service.ChamadoService;

import com.helpdesk.api.mapper.ChamadoMapper;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.helpdesk.api.mapper.ChamadoMapper.*;

@RestController
@RequestMapping("/api/chamados")
public class ChamadoController {

    private final ChamadoService chamadoService;

    @Autowired
    public ChamadoController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @PostMapping
    public ResponseEntity<?> createChamado(@Valid @RequestBody ChamadoDTO chamadoDTO) {
        chamadoService.createChamado(toEntityChamado(chamadoDTO));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> getAllChamados() {
        List<Chamado> chamados = chamadoService.getAllChamados();
        return ResponseEntity.ok(toDtoChamado(chamados));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> getChamadoById(@Valid @PathVariable("id") Long id) {
        Optional<Chamado> chamado = chamadoService.getChamadoById(id);
        return chamado.map(value -> ResponseEntity.ok(toDtoChamadoDto(value))).orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Chamado>> getChamadosPorEstado(@PathVariable EstadoChamado estado) {
        List<Chamado> chamados = chamadoService.getChamadosPorEstado(estado);
        return ResponseEntity.ok(chamados);
    }

    //    @PutMapping("/{id}/estado")
//    public ResponseEntity<ChamadoDTO> updateEstadoChamado(@PathVariable Long id, @RequestBody EstadoChamado novoEstado) {
//
//    }
    @PutMapping("/{id}")
    public ResponseEntity<ChamadoDTO> updateChamado(@PathVariable Long id, @RequestBody ChamadoDTO chamadoDTO) {
        Optional<Chamado> updatedChamado = chamadoService.updateChamado(id, ChamadoMapper.toEntityChamado(chamadoDTO));

        return updatedChamado.map(chamado -> ResponseEntity.ok(ChamadoMapper.toDtoChamadoDto(chamado))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteChamado(@Valid @PathVariable("id") Long id) {
        chamadoService.deleteChamado(id);
        return ResponseEntity.noContent().build();
    }

}
