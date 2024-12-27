package com.helpdesk.api.controller;

import com.helpdesk.api.model.dto.HorariosDisponiveisDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horariosDisponiveis")
public interface HorariosDisponiveisController {

    @GetMapping
    ResponseEntity<List<HorariosDisponiveisDTO>> findAll();

    @PostMapping
    ResponseEntity<HorariosDisponiveisDTO> createHorariosDisponiveis(@RequestBody HorariosDisponiveisDTO horariosDisponiveisDTO);
}
