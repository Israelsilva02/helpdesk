package com.helpdesk.api.controller;

import com.helpdesk.api.model.dto.BalcaoDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/balcoes")
public interface BalcaoController {

    @PostMapping
    ResponseEntity<BalcaoDTO> createBalcaoAtendimento(@Valid @RequestBody BalcaoDTO balcaoDTO);

    @GetMapping
    ResponseEntity<List<BalcaoDTO>> getAllBalcoes();

    @GetMapping("/{id}")
    ResponseEntity<BalcaoDTO> getBalcaoById(@PathVariable("id") Long id);

    @PutMapping("/{id}")
    ResponseEntity<BalcaoDTO> updateBalcao(@PathVariable("id") Long id, @Valid @RequestBody BalcaoDTO updatedBalcaoDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteBalcao(@PathVariable("id") Long id);
}
