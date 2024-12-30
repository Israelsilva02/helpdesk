package com.helpdesk.api.controller.impl;

import com.helpdesk.api.controller.ChamadoController;
import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.dto.ChamadoDTO;
import com.helpdesk.api.enums.EstadoChamado;
import com.helpdesk.api.model.dto.VisualizarChamadoDTO;
import com.helpdesk.api.service.ChamadoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChamadoControllerImpl implements ChamadoController {

    private final ChamadoServiceImpl chamadoServiceImpl;

    @Override
    public ResponseEntity<VisualizarChamadoDTO> createChamado(@Valid @RequestBody ChamadoDTO chamadoDTO) {
        VisualizarChamadoDTO createdChamado = chamadoServiceImpl.createChamado(chamadoDTO);
        return ResponseEntity.ok(createdChamado);
    }

    @Override
    public ResponseEntity<List<ChamadoDTO>> getAllChamados() {
        List<ChamadoDTO> chamados = chamadoServiceImpl.getAllChamados();
        return ResponseEntity.ok(chamados);
    }

    @Override
    public ResponseEntity<ChamadoDTO> getChamadoById(Long id) {
        ChamadoDTO chamadoDTO = chamadoServiceImpl.getChamadoById(id);
        return ResponseEntity.ok(chamadoDTO);
    }

    @Override
    public ResponseEntity<List<ChamadoDTO>> getChamadosPorEstado(EstadoChamado estado) {
        List<ChamadoDTO> chamados = chamadoServiceImpl.getChamadosPorEstado(estado);
        return ResponseEntity.ok(chamados);
    }

    @Override
    public ResponseEntity<ChamadoDTO> updateChamado(Long id, @Valid @RequestBody ChamadoDTO chamadoDTO) {
        ChamadoDTO chamadoDTOupdate = chamadoServiceImpl.updateChamado(id, chamadoDTO);
        return ResponseEntity.ok(chamadoDTOupdate);
    }

    @Override
    public ResponseEntity<Void> deleteChamado(Long id) {
        chamadoServiceImpl.deleteChamado(id);
        return ResponseEntity.noContent().build();
    }
}
