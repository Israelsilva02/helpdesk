package com.helpdesk.api.controller.impl;

import com.helpdesk.api.controller.BalcaoController;
import com.helpdesk.api.model.dto.BalcaoDTO;
import com.helpdesk.api.service.BalcaoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalcaoControllerImpl implements BalcaoController {

    private final BalcaoServiceImpl balcaoServiceImpl;

    @Override
    public ResponseEntity<BalcaoDTO> createBalcaoAtendimento(@Valid @RequestBody BalcaoDTO balcaoDTO) {
        BalcaoDTO createBalcao = balcaoServiceImpl.createBalcaoAtendimento(balcaoDTO);
        return ResponseEntity.ok(createBalcao);
    }

    @Override
    public ResponseEntity<List<BalcaoDTO>> getAllBalcoes() {
        List<BalcaoDTO> balcos = balcaoServiceImpl.getAllBalcoes();
        return ResponseEntity.ok(balcos);
    }

    @Override
    public ResponseEntity<BalcaoDTO> getBalcaoById(Long id) {
        BalcaoDTO balcao = balcaoServiceImpl.getBalcaoById(id);
        return ResponseEntity.ok(balcao);
    }

    @Override
    public ResponseEntity<BalcaoDTO> updateBalcao(Long id, @Valid @RequestBody BalcaoDTO updatedBalcaoDTO) {
        BalcaoDTO updatedBalcao = balcaoServiceImpl.updateBalcao(id, updatedBalcaoDTO);
        return ResponseEntity.ok(updatedBalcao);
    }

    @Override
    public ResponseEntity<Void> deleteBalcao(Long id) {
        balcaoServiceImpl.deleteBalcao(id);
        return ResponseEntity.noContent().build();
    }
}
