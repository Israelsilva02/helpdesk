package com.helpdesk.api.controller;

import com.helpdesk.api.model.AtendenteBalcao;
import com.helpdesk.api.model.HorariosDisponiveis;
import com.helpdesk.api.model.dto.HorariosDisponiveisDTO;
import com.helpdesk.api.repository.AtendenteBalcaoRepository;
import com.helpdesk.api.repository.HorariosDisponiveisRepository;
import com.helpdesk.api.service.HorariosDisponiveisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.helpdesk.api.mapper.HorariosDisponiveisMappeer.toDtoHorariosDisponiveis;
import static com.helpdesk.api.mapper.HorariosDisponiveisMappeer.toEntityHorariosDisponiveis;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/horariosDisponiveis")
public class HorariosDisponiveisController {
    @Autowired
    private HorariosDisponiveisService horariosDisponiveisService;

    @Autowired
    private AtendenteBalcaoRepository atendenteBalcaoRepository;

    @GetMapping
    public ResponseEntity<List<HorariosDisponiveisDTO>> findAll() {
        List<HorariosDisponiveis> horariosDisponiveis = horariosDisponiveisService.findAll();
        return ResponseEntity.ok(toDtoHorariosDisponiveis(horariosDisponiveis));
    }

    @PostMapping
    public ResponseEntity<HorariosDisponiveis> createHorariosDisponiveis(@Valid @RequestBody HorariosDisponiveisDTO horariosDisponiveisDTO) {
        Optional<AtendenteBalcao> atendenteBalcaoOptional = atendenteBalcaoRepository.findById(horariosDisponiveisDTO.getIdAtendenteBalcao());
        if (atendenteBalcaoOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        horariosDisponiveisService.createHorario(toEntityHorariosDisponiveis(horariosDisponiveisDTO));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
