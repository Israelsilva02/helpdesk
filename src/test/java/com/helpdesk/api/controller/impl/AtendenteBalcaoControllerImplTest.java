package com.helpdesk.api.controller.impl;

import com.helpdesk.api.model.dto.AtendenteBalcaoDTO;
import com.helpdesk.api.service.AtendenteBalcaoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AtendenteBalcaoControllerImplTest {

    private AtendenteBalcaoControllerImpl atendenteController;
    private AtendenteBalcaoServiceImpl atendenteService;

    @BeforeEach
    void setUp() {
        atendenteService = mock(AtendenteBalcaoServiceImpl.class);
        atendenteController = new AtendenteBalcaoControllerImpl(atendenteService);
    }

    @Test
    void deveCriarAtendente() {
        AtendenteBalcaoDTO atendente = new AtendenteBalcaoDTO();
        atendente.setNome("israel");

        when(atendenteService.createAtendente(any(AtendenteBalcaoDTO.class)))
                .thenReturn(atendente);

        ResponseEntity<AtendenteBalcaoDTO> response = atendenteController.create(atendente);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getNome()).isEqualTo("israel");

        verify(atendenteService).createAtendente(any(AtendenteBalcaoDTO.class));
    }

    @Test
    void deveBuscarAtendentePorId() {
        AtendenteBalcaoDTO atendente = new AtendenteBalcaoDTO();
        atendente.setNome("israel");

        when(atendenteService.getAtendenteById(1L)).thenReturn(atendente);

        ResponseEntity<AtendenteBalcaoDTO> response = atendenteController.getAtendenteById(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getNome()).isEqualTo("israel");

        verify(atendenteService).getAtendenteById(1L);
    }

    @Test
    void deveListarTodosAtendentes() {
        AtendenteBalcaoDTO atendente1 = new AtendenteBalcaoDTO();
        AtendenteBalcaoDTO atendente2 = new AtendenteBalcaoDTO();

        List<AtendenteBalcaoDTO> atendentesList = Arrays.asList(atendente1, atendente2);

        when(atendenteService.getAllAtendentes()).thenReturn(atendentesList);

        ResponseEntity<List<AtendenteBalcaoDTO>> response = atendenteController.getAllAtendentes();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(atendentesList.size());

        verify(atendenteService).getAllAtendentes();
    }

    @Test
    void deveAtualizarAtendente() {
        AtendenteBalcaoDTO atendente = new AtendenteBalcaoDTO();
        atendente.setNome("israel bernardo");

        when(atendenteService.updateAtendente(eq(1L), any(AtendenteBalcaoDTO.class)))
                .thenReturn(atendente);

        ResponseEntity<AtendenteBalcaoDTO> response = atendenteController.updateAtendente(1L, atendente);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getNome()).isEqualTo("israel bernardo");

        verify(atendenteService).updateAtendente(eq(1L), any(AtendenteBalcaoDTO.class));
    }

    @Test
    void deveDeletarAtendente() {
        doNothing().when(atendenteService).deleteAtendente(1L);

        ResponseEntity<Void> response = atendenteController.deleteAtendente(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);

        verify(atendenteService).deleteAtendente(1L);
    }
}
