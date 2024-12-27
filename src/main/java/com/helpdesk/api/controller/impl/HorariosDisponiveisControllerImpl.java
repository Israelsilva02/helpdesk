package com.helpdesk.api.controller.impl;

import com.helpdesk.api.controller.HorariosDisponiveisController;
import com.helpdesk.api.model.dto.HorariosDisponiveisDTO;
import com.helpdesk.api.service.HorariosDisponiveisServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HorariosDisponiveisControllerImpl implements HorariosDisponiveisController {

    private final HorariosDisponiveisServiceImpl horariosDisponiveisServiceImpl;

    @Override
    public ResponseEntity<List<HorariosDisponiveisDTO>> findAll() {
        List<HorariosDisponiveisDTO> horariosDisponiveis = horariosDisponiveisServiceImpl.findAll();
        return ResponseEntity.ok(horariosDisponiveis);
    }

    @Override
    public ResponseEntity<HorariosDisponiveisDTO> createHorariosDisponiveis(@Valid @RequestBody HorariosDisponiveisDTO horariosDisponiveisDTO) {
        HorariosDisponiveisDTO createHorariosDisponiveisDTO = horariosDisponiveisServiceImpl.createHorario(horariosDisponiveisDTO);
        return ResponseEntity.ok(createHorariosDisponiveisDTO);
    }
}
