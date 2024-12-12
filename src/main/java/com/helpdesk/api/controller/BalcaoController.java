package com.helpdesk.api.controller;

import com.helpdesk.api.model.dto.BalcaoDTO;

import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.service.BalcaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.helpdesk.api.mapper.BalcaoMapper.*;

@RestController
@RequestMapping("/api/balcoes")
public class BalcaoController {

    private final BalcaoService balcaoService;

    @Autowired
    public BalcaoController(BalcaoService balcaoService) {
        this.balcaoService = balcaoService;
    }

    @PostMapping
    public ResponseEntity<?> createBalcaoAtendimento(@Valid @RequestBody BalcaoDTO balcaoDTO) {
        balcaoService.createBalcaoAtendimento(toEntityBalcao(balcaoDTO));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<BalcaoDTO>> getAllBalcoes() {
        List<Balcao> balcoes = balcaoService.getAllBalcoes();
        return ResponseEntity.ok(toDtoBalcao(balcoes));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BalcaoDTO> getBalcaoById(@PathVariable("id") Long id) {
        Optional<Balcao> balcao = balcaoService.getBalcaoById(id);
        return balcao.map(value -> ResponseEntity.ok(toDtoBalcaoDto(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BalcaoDTO> updateBalcao(@PathVariable("id") Long id, @Valid @RequestBody BalcaoDTO updatedBalcaoDTO) {
        Optional<Balcao> balcao = balcaoService.updateBalcao(id, toEntityBalcao(updatedBalcaoDTO));
        if (balcao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedBalcaoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBalcao(@PathVariable("id") Long id) {
        balcaoService.deleteBalcao(id);
        return ResponseEntity.noContent().build();
    }
}
