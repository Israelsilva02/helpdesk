package com.helpdesk.api.controller;

import com.helpdesk.api.model.dto.AtendenteBalcaoDTO;
import com.helpdesk.api.service.AtendenteBalcaoServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/atendentes")
public class AtendenteBalcaoController {

    private AtendenteBalcaoServiceImpl atendenteBalcaoService;

    @GetMapping
    public ResponseEntity<List<AtendenteBalcaoDTO>> getAllAtendentes() {
        List<AtendenteBalcaoDTO> atendentes = atendenteBalcaoService.findAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(atendentes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtendenteBalcaoDTO> getAtendenteById(@PathVariable("id") Long id) {
        Optional<AtendenteBalcaoDTO> atendente = atendenteBalcaoService.findById(id);
        return atendente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AtendenteBalcaoDTO> createAtendente(@RequestBody AtendenteBalcaoDTO atendenteDTO) {
        AtendenteBalcaoDTO savedAtendenteDTO = atendenteBalcaoService.save(atendenteDTO);
        return ResponseEntity.ok(savedAtendenteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtendente(@PathVariable("id") Long id) {
        atendenteBalcaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
