package com.helpdesk.api.controller.impl;

import com.helpdesk.api.model.dto.HorariosDisponiveisDTO;
import com.helpdesk.api.service.HorariosDisponiveisServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HorariosDisponiveisControllerImplTest {

    private HorariosDisponiveisControllerImpl horariosController;
    private HorariosDisponiveisServiceImpl horariosService;

    @BeforeEach
    void setUp() {
        horariosService = mock(HorariosDisponiveisServiceImpl.class);
        horariosController = new HorariosDisponiveisControllerImpl(horariosService);
    }

    @Test
    void deveListarTodosHorariosDisponiveis() {
        HorariosDisponiveisDTO horario = new HorariosDisponiveisDTO();
        horario.setId(1L);
        horario.setHorariosDisponiveis(LocalDateTime.parse("2024-12-27T09:00:00"));

        List<HorariosDisponiveisDTO> horariosList = Arrays.asList(horario);

        when(horariosService.findAll()).thenReturn(horariosList);

        ResponseEntity<List<HorariosDisponiveisDTO>> response = horariosController.findAll();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(horariosList.size());
        assertThat(response.getBody().get(0).getHorariosDisponiveis()).isEqualTo("2024-12-27T09:00:00");

        verify(horariosService).findAll();
    }

    @Test
    void deveCriarHorarioDisponivel() {
        HorariosDisponiveisDTO horario = new HorariosDisponiveisDTO();
        horario.setId(1L);
        horario.setHorariosDisponiveis(LocalDateTime.parse("2024-12-27T09:00:00"));

        when(horariosService.createHorario(any(HorariosDisponiveisDTO.class))).thenReturn(horario);


        ResponseEntity<HorariosDisponiveisDTO> response = horariosController.createHorariosDisponiveis(horario);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getHorariosDisponiveis()).isEqualTo("2024-12-27T09:00:00");

        verify(horariosService).createHorario(any(HorariosDisponiveisDTO.class));
    }
}
