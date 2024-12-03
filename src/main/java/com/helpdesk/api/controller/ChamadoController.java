package com.helpdesk.api.controller;

import com.helpdesk.api.exception.BalcaoException;
import com.helpdesk.api.exception.ChamadoException;
import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.EstadoChamado;
import com.helpdesk.api.service.ChamadoService;
import com.helpdesk.api.util.MessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chamados")
public class ChamadoController {

    private final ChamadoService chamadoService;

    @Autowired
    public ChamadoController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @PostMapping
    public ResponseEntity<Chamado> createChamado(@RequestBody Chamado chamado) throws Exception {
        Chamado novoChamado = chamadoService.createChamado(chamado);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoChamado);
    }

    @GetMapping
    public ResponseEntity<Page<Chamado>> getChamadosByCustomerId(@RequestParam Long customerId, Pageable pageable) {
        Page<Chamado> chamados = chamadoService.getChamadosByCustomerId(customerId, pageable);
        return ResponseEntity.ok(chamados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chamado> getChamadoById(@PathVariable Long id) {
        Chamado chamado = chamadoService.getChamadoById(id)
                .orElseThrow(() -> new ChamadoException(MessageConstants.CHAMADO_NAO_ENCONTRADO_C0M_ID + id));
        return ResponseEntity.ok(chamado);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Chamado> updateEstadoChamado(@PathVariable Long id, @RequestBody EstadoChamado novoEstado) throws BalcaoException {
        Chamado chamadoAtualizado = chamadoService.updateEstadoChamado(id, novoEstado);
        return ResponseEntity.ok(chamadoAtualizado);
    }
}