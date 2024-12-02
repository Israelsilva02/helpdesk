package com.helpdesk.api.controller;

import com.helpdesk.api.exception.BalcaoException;
import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.service.BalcaoService;
import com.helpdesk.api.util.MessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/balcoes")
public class BalcaoController {

    private final BalcaoService balcaoService;

    @Autowired
    public BalcaoController(BalcaoService balcaoService) {
        this.balcaoService = balcaoService;
    }

    @PostMapping
    public ResponseEntity<Balcao> createBalcao(@RequestBody Balcao balcao) throws BalcaoException {
        Balcao novoBalcao = balcaoService.createBalcao(balcao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoBalcao);
    }

    @GetMapping
    public ResponseEntity<List<Balcao>> getAllBalcoes() throws BalcaoException {
        List<Balcao> balcoes = balcaoService.getAllBalcoes();
        return ResponseEntity.ok(balcoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Balcao> getBalcaoById(@PathVariable Long id) throws BalcaoException {
        Balcao balcao = balcaoService.getBalcaoById(id)
                .orElseThrow(() -> new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_C0M_ID + id));
        return ResponseEntity.ok(balcao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Balcao> updateBalcao(@PathVariable Long id, @RequestBody Balcao updatedBalcao) throws BalcaoException {
        Balcao balcaoAtualizado = balcaoService.updateBalcao(id, updatedBalcao);
        return ResponseEntity.ok(balcaoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBalcao(@PathVariable Long id) throws BalcaoException {
        balcaoService.deleteBalcao(id);
        return ResponseEntity.noContent().build();
    }
}