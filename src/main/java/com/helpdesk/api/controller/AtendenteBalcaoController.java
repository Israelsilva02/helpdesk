package com.helpdesk.api.controller;

import com.helpdesk.api.anotations.ApiController;
import com.helpdesk.api.anotations.SpringDocApiResponseUtil;
import com.helpdesk.api.model.dto.AtendenteBalcaoDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@RequestMapping("/api/atendentes")
public interface AtendenteBalcaoController {

    @PostMapping("/cadastrar")
    @SpringDocApiResponseUtil(summary = "Registrar um atendente no sistema")
    ResponseEntity<AtendenteBalcaoDTO> create(@RequestBody @Valid AtendenteBalcaoDTO atendenteBalcaoDTO);

    @GetMapping("/{id}")
    @SpringDocApiResponseUtil(summary = "Obter detalhes de um atendente pelo ID")
    ResponseEntity<AtendenteBalcaoDTO> getAtendenteById(@PathVariable("id") Long id);

    @GetMapping
    @SpringDocApiResponseUtil(summary = "Listar todos os atendentes")
    ResponseEntity<List<AtendenteBalcaoDTO>> getAllAtendentes();

    @PutMapping("/{id}")
    @SpringDocApiResponseUtil(summary = "Atualizar informações de um atendente")
    ResponseEntity<AtendenteBalcaoDTO> updateAtendente(@PathVariable("id") Long id, @RequestBody @Valid AtendenteBalcaoDTO atendenteDTO);

    @DeleteMapping("/{id}")
    @SpringDocApiResponseUtil(summary = "Remover um atendente do sistema")
    ResponseEntity<Void> deleteAtendente(@PathVariable("id") Long id);
}
