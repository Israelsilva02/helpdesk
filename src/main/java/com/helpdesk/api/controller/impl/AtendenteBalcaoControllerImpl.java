package com.helpdesk.api.controller.impl;

import com.helpdesk.api.anotations.ApiController;
import com.helpdesk.api.controller.AtendenteBalcaoController;
import com.helpdesk.api.model.dto.AtendenteBalcaoDTO;
import com.helpdesk.api.service.AtendenteBalcaoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AtendenteBalcaoControllerImpl implements AtendenteBalcaoController {

    private final AtendenteBalcaoServiceImpl atendenteService;

    @Override
    public ResponseEntity<AtendenteBalcaoDTO> create(AtendenteBalcaoDTO atendenteBalcaoDTO) {
        return ResponseEntity.ok(atendenteService.createAtendente(atendenteBalcaoDTO));
    }

    @Override
    public ResponseEntity<AtendenteBalcaoDTO> getAtendenteById(Long id) {
        AtendenteBalcaoDTO atendente = atendenteService.getAtendenteById(id);
        return ResponseEntity.ok().body(atendente);
    }

    @Override
    public ResponseEntity<List<AtendenteBalcaoDTO>> getAllAtendentes() {
        List<AtendenteBalcaoDTO> atendentes = atendenteService.getAllAtendentes();
        return ResponseEntity.ok(atendentes);
    }

    @Override
    public ResponseEntity<AtendenteBalcaoDTO> updateAtendente(Long id, AtendenteBalcaoDTO atendenteDTO) {
        AtendenteBalcaoDTO atualizado = atendenteService.updateAtendente(id, atendenteDTO);
        return ResponseEntity.ok(atualizado);
    }

    @Override
    public ResponseEntity<Void> deleteAtendente(Long id) {
        atendenteService.deleteAtendente(id);
        return ResponseEntity.noContent().build();
    }
}
