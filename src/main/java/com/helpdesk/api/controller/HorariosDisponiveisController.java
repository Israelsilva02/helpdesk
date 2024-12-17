package com.helpdesk.api.controller;

import com.helpdesk.api.model.dto.HorariosDisponiveisDTO;

import com.helpdesk.api.service.HorariosDisponiveisServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/horariosDisponiveis")
public class HorariosDisponiveisController {

    private HorariosDisponiveisServiceImpl horariosDisponiveisServiceImpl;

    @GetMapping
    public ResponseEntity<List<HorariosDisponiveisDTO>> findAll() {
        List<HorariosDisponiveisDTO> horariosDisponiveis = horariosDisponiveisServiceImpl.findAll();
        return ResponseEntity.ok(horariosDisponiveis);
    }

    @PostMapping
    public ResponseEntity<HorariosDisponiveisDTO> createHorariosDisponiveis(@Valid @RequestBody HorariosDisponiveisDTO horariosDisponiveisDTO) {
        HorariosDisponiveisDTO createHorariosDisponiveisDTO = horariosDisponiveisServiceImpl.createHorario(horariosDisponiveisDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createHorariosDisponiveisDTO);
    }
}
