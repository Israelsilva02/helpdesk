package com.helpdesk.api.controller.impl;

import com.helpdesk.api.enums.EstadoChamado;
import com.helpdesk.api.model.dto.ChamadoDTO;
import com.helpdesk.api.model.dto.VisualizarChamadoDTO;
import com.helpdesk.api.service.ChamadoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ChamadoControllerImplTest {

    private ChamadoControllerImpl chamadoController;
    private ChamadoServiceImpl chamadoService;

    @BeforeEach
    void setUp() {
        chamadoService = mock(ChamadoServiceImpl.class);
        chamadoController = new ChamadoControllerImpl(chamadoService);
    }

    @Test
    void deveCriarChamado() {
        ChamadoDTO chamadoDTO = new ChamadoDTO();
        chamadoDTO.setMotivoChamado("Precisamos de suporte técnico para o dispositivo Playstation 1");
        VisualizarChamadoDTO visualizarChamadoDTO = new VisualizarChamadoDTO();
        visualizarChamadoDTO.setMotivoChamado("Precisamos de suporte técnico para o dispositivo Playstation 1");

        when(chamadoService.createChamado(any(ChamadoDTO.class)))
                .thenReturn(visualizarChamadoDTO);

        ResponseEntity<VisualizarChamadoDTO> response = chamadoController.createChamado(chamadoDTO);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMotivoChamado()).isEqualTo("Precisamos de suporte técnico para o dispositivo Playstation 1");

        verify(chamadoService).createChamado(any(ChamadoDTO.class));
    }

    @Test
    void deveListarTodosChamados() {
        ChamadoDTO chamado1 = new ChamadoDTO();
        ChamadoDTO chamado2 = new ChamadoDTO();

        List<ChamadoDTO> chamadosList = Arrays.asList(chamado1, chamado2);

        when(chamadoService.getAllChamados()).thenReturn(chamadosList);

        ResponseEntity<List<ChamadoDTO>> response = chamadoController.getAllChamados();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(chamadosList.size());

        verify(chamadoService).getAllChamados();
    }

    @Test
    void deveBuscarChamadoPorId() {
        ChamadoDTO chamadoDTO = new ChamadoDTO();
        chamadoDTO.setMotivoChamado("Chamado para maquina de impressao");

        when(chamadoService.getChamadoById(1L)).thenReturn(chamadoDTO);

        ResponseEntity<ChamadoDTO> response = chamadoController.getChamadoById(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMotivoChamado()).isEqualTo("Chamado para maquina de impressao");

        verify(chamadoService).getChamadoById(1L);
    }

    @Test
    void deveBuscarChamadosPorEstado() {
        ChamadoDTO chamadoDTO = new ChamadoDTO();
        List<ChamadoDTO> chamadosList = Arrays.asList(chamadoDTO);

        when(chamadoService.getChamadosPorEstado(EstadoChamado.ABERTO))
                .thenReturn(chamadosList);

        ResponseEntity<List<ChamadoDTO>> response = chamadoController.getChamadosPorEstado(EstadoChamado.ABERTO);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(chamadosList.size());

        verify(chamadoService).getChamadosPorEstado(EstadoChamado.ABERTO);
    }

    @Test
    void deveAtualizarChamado() {
        ChamadoDTO chamadoDTO = new ChamadoDTO();
        chamadoDTO.setMotivoChamado("Chamado Atualizado");

        when(chamadoService.updateChamado(eq(1L), any(ChamadoDTO.class)))
                .thenReturn(chamadoDTO);

        ResponseEntity<ChamadoDTO> response = chamadoController.updateChamado(1L, chamadoDTO);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMotivoChamado()).isEqualTo("Chamado Atualizado");

        verify(chamadoService).updateChamado(eq(1L), any(ChamadoDTO.class));
    }

    @Test
    void deveDeletarChamado() {
        doNothing().when(chamadoService).deleteChamado(1L);

        ResponseEntity<Void> response = chamadoController.deleteChamado(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);

        verify(chamadoService).deleteChamado(1L);
    }
}
