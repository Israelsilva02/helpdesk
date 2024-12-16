package com.helpdesk.api.controller;


import com.helpdesk.api.model.dto.BalcaoDTO;
import com.helpdesk.api.service.BalcaoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/balcoes")
public class BalcaoController {

    private final BalcaoServiceImpl balcaoServiceImpl;


    @PostMapping
    public ResponseEntity<BalcaoDTO> createBalcaoAtendimento(@Valid @RequestBody BalcaoDTO balcaoDTO) {
        BalcaoDTO createBalcao = balcaoServiceImpl.createBalcaoAtendimento(balcaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createBalcao);
    }

    @GetMapping
    public ResponseEntity<List<BalcaoDTO>> getAllBalcoes() {
        List<BalcaoDTO> balcoes = balcaoServiceImpl.getAllBalcoes();
        return ResponseEntity.ok(balcoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BalcaoDTO> getBalcaoById(@PathVariable("id") Long id) {
        return balcaoServiceImpl.getBalcaoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BalcaoDTO> updateBalcao(@PathVariable("id") Long id, @Valid @RequestBody BalcaoDTO updatedBalcaoDTO) {
        return balcaoServiceImpl.updateBalcao(id, updatedBalcaoDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBalcao(@PathVariable("id") Long id) {
        balcaoServiceImpl.deleteBalcao(id);
        return ResponseEntity.noContent().build();
    }
}
