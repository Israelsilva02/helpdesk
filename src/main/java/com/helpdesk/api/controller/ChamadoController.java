package com.helpdesk.api.controller;

import com.helpdesk.api.model.dto.ChamadoDTO;
import com.helpdesk.api.exception.ChamadoException;
import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.EstadoChamado;
import com.helpdesk.api.service.ChamadoService;
import com.helpdesk.api.util.MessageConstants;
import com.helpdesk.api.mapper.ChamadoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chamados")
public class ChamadoController {

    private final ChamadoService chamadoService;

    @Autowired
    public ChamadoController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> createChamado(@RequestBody ChamadoDTO ChamadoDTO) throws Exception {
        Chamado chamado = ChamadoMapper.toEntity(ChamadoDTO);
        Chamado novoChamado = chamadoService.createChamado(chamado);
        ChamadoDTO novoChamadoDTO = ChamadoMapper.toDto(novoChamado);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoChamadoDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ChamadoDTO>> getChamadosByCustomerId(@RequestParam Long customerId, Pageable pageable) {
        Page<Chamado> chamados = chamadoService.getChamadosByCustomerId(customerId, pageable);
        Page<ChamadoDTO> chamadosDto = chamados.map(ChamadoMapper::toDto);
        return ResponseEntity.ok(chamadosDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> getChamadoById(@PathVariable Long id) {
        Chamado chamado = chamadoService.getChamadoById(id)
                .orElseThrow(() -> new ChamadoException(MessageConstants.CHAMADO_NAO_ENCONTRADO_C0M_ID + id));
        ChamadoDTO ChamadoDTO = ChamadoMapper.toDto(chamado);
        return ResponseEntity.ok(ChamadoDTO);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ChamadoDTO>> getChamadosPorEstado(@PathVariable EstadoChamado estado) {
        List<Chamado> chamados = chamadoService.getChamadosPorEstado(estado);
        List<ChamadoDTO> chamadosDto = ChamadoMapper.toDtoList(chamados);
        return ResponseEntity.ok(chamadosDto);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<ChamadoDTO> updateEstadoChamado(@PathVariable Long id, @RequestBody EstadoChamado novoEstado) throws ChamadoException {
        Chamado chamadoAtualizado = chamadoService.updateEstadoChamado(id, novoEstado);
        ChamadoDTO ChamadoDTOAtualizado = ChamadoMapper.toDto(chamadoAtualizado);
        return ResponseEntity.ok(ChamadoDTOAtualizado);
    }
}
