package com.helpdesk.api.controller.impl;

import com.helpdesk.api.model.dto.EquipamentoDTO;
import com.helpdesk.api.service.EquipamentoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class EquipamentoControllerImplTest {

    private EquipamentoControllerImpl equipamentoController;
    private EquipamentoServiceImpl equipamentoService;

    @BeforeEach
    void setUp() {
        equipamentoService = mock(EquipamentoServiceImpl.class);
        equipamentoController = new EquipamentoControllerImpl(equipamentoService);
    }

    @Test
    void deveListarTodosEquipamentos() {
        EquipamentoDTO equipamento = new EquipamentoDTO();
        equipamento.setDeviceId(1L);
        equipamento.setSerialNumber("123456789");

        List<EquipamentoDTO> equipamentosList = Arrays.asList(equipamento);

        when(equipamentoService.findAll()).thenReturn(equipamentosList);

        ResponseEntity<List<EquipamentoDTO>> response = equipamentoController.findAll();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(equipamentosList.size());
        assertThat(response.getBody().get(0).getSerialNumber()).isEqualTo("123456789");

        verify(equipamentoService).findAll();
    }

    @Test
    void deveBuscarEquipamentoPorId() {
        EquipamentoDTO equipamento = new EquipamentoDTO();
        equipamento.setDeviceId(1L);
        equipamento.setSerialNumber("12121212");

        when(equipamentoService.findById(1L)).thenReturn(equipamento);

        ResponseEntity<EquipamentoDTO> response = equipamentoController.findById(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getSerialNumber()).isEqualTo("12121212");

        verify(equipamentoService).findById(1L);
    }

    @Test
    void deveSalvarEquipamento() {
        EquipamentoDTO equipamento = new EquipamentoDTO();
        equipamento.setDeviceId(1L);
        equipamento.setSerialNumber("32323232");

        when(equipamentoService.save(any(EquipamentoDTO.class)))
                .thenReturn(equipamento);

        ResponseEntity<EquipamentoDTO> response = equipamentoController.save(equipamento);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getSerialNumber()).isEqualTo("32323232");

        verify(equipamentoService).save(any(EquipamentoDTO.class));
    }

    @Test
    void deveAtualizarEquipamento() {
        EquipamentoDTO equipamento = new EquipamentoDTO();
        equipamento.setDeviceId(1L);
        equipamento.setSerialNumber("54545454");

        when(equipamentoService.update(eq(1L), any(EquipamentoDTO.class)))
                .thenReturn(equipamento);

        ResponseEntity<EquipamentoDTO> response = equipamentoController.update(1L, equipamento);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getSerialNumber()).isEqualTo("54545454");

        verify(equipamentoService).update(eq(1L), any(EquipamentoDTO.class));
    }

    @Test
    void deveDeletarEquipamento() {
        doNothing().when(equipamentoService).delete(1L);

        ResponseEntity<Void> response = equipamentoController.delete(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);

        verify(equipamentoService).delete(1L);
    }
}
