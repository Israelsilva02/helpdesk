package com.helpdesk.api.controller;

import com.helpdesk.api.model.dto.BalcaoDTO;
import com.helpdesk.api.exception.BalcaoException;
import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.service.BalcaoService;
import com.helpdesk.api.util.MessageConstants;
import com.helpdesk.api.mapper.BalcaoMapper;
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
    public ResponseEntity<BalcaoDTO> createBalcaoAtendimento(@RequestBody BalcaoDTO BalcaoDTO) {
        Balcao balcao = BalcaoMapper.toEntity(BalcaoDTO);
        Balcao novoBalcao = balcaoService.createBalcaoAtendimento(balcao);
        BalcaoDTO novoBalcaoDTO = BalcaoMapper.toDto(novoBalcao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoBalcaoDTO);
    }

    @GetMapping
    public ResponseEntity<List<BalcaoDTO>> getAllBalcoes() throws BalcaoException {
        List<Balcao> balcoes = balcaoService.getAllBalcoes();
        List<BalcaoDTO> balcoesDto = BalcaoMapper.toDtoList(balcoes);
        return ResponseEntity.ok(balcoesDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BalcaoDTO> getBalcaoById(@PathVariable Long id) throws BalcaoException {
        Balcao balcao = balcaoService.getBalcaoById(id)
                .orElseThrow(() -> new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_C0M_ID + id));
        BalcaoDTO BalcaoDTO = BalcaoMapper.toDto(balcao);
        return ResponseEntity.ok(BalcaoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BalcaoDTO> updateBalcao(@PathVariable Long id, @RequestBody BalcaoDTO updatedBalcaoDTO) throws BalcaoException {
        Balcao updatedBalcao = BalcaoMapper.toEntity(updatedBalcaoDTO);
        Balcao balcaoAtualizado = balcaoService.updateBalcao(id, updatedBalcao);
        BalcaoDTO BalcaoDTOAtualizado = BalcaoMapper.toDto(balcaoAtualizado);
        return ResponseEntity.ok(BalcaoDTOAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBalcao(@PathVariable Long id) throws BalcaoException {
        balcaoService.deleteBalcao(id);
        return ResponseEntity.noContent().build();
    }
}
