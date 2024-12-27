package com.helpdesk.api.controller.impl;

import com.helpdesk.api.model.dto.BalcaoDTO;
import com.helpdesk.api.service.BalcaoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BalcaoControllerImplTest {

    private BalcaoControllerImpl balcaoController;
    private BalcaoServiceImpl balcaoService;

    @BeforeEach
    void setUp() {
        balcaoService = mock(BalcaoServiceImpl.class);
        balcaoController = new BalcaoControllerImpl(balcaoService);
    }

    @Test
    void deveCriarBalcaoAtendimento() {
        BalcaoDTO balcaoDTO = new BalcaoDTO();
        balcaoDTO.setAtendente(1L);

        when(balcaoService.createBalcaoAtendimento(any(BalcaoDTO.class)))
                .thenReturn(balcaoDTO);

        ResponseEntity<BalcaoDTO> response = balcaoController.createBalcaoAtendimento(balcaoDTO);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getAtendente()).isEqualTo(1L);

        verify(balcaoService).createBalcaoAtendimento(any(BalcaoDTO.class));
    }

    @Test
    void deveListarTodosBalcoes() {
        BalcaoDTO balcao1 = new BalcaoDTO();
        BalcaoDTO balcao2 = new BalcaoDTO();

        List<BalcaoDTO> balcosList = Arrays.asList(balcao1, balcao2);

        when(balcaoService.getAllBalcoes()).thenReturn(balcosList);

        ResponseEntity<List<BalcaoDTO>> response = balcaoController.getAllBalcoes();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(balcosList.size());

        verify(balcaoService).getAllBalcoes();
    }

    @Test
    void deveBuscarBalcaoPorId() {
        BalcaoDTO balcaoDTO = new BalcaoDTO();
        balcaoDTO.setAtendente(1L);

        when(balcaoService.getBalcaoById(1L)).thenReturn(balcaoDTO);

        ResponseEntity<BalcaoDTO> response = balcaoController.getBalcaoById(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getAtendente()).isEqualTo(1L);

        verify(balcaoService).getBalcaoById(1L);
    }

    @Test
    void deveAtualizarBalcao() {
        BalcaoDTO balcaoDTO = new BalcaoDTO();
        balcaoDTO.setAtendente(1L);

        when(balcaoService.updateBalcao(eq(1L), any(BalcaoDTO.class)))
                .thenReturn(balcaoDTO);

        ResponseEntity<BalcaoDTO> response = balcaoController.updateBalcao(1L, balcaoDTO);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getAtendente()).isEqualTo(1L);

        verify(balcaoService).updateBalcao(eq(1L), any(BalcaoDTO.class));
    }

    @Test
    void deveDeletarBalcao() {
        doNothing().when(balcaoService).deleteBalcao(1L);

        ResponseEntity<Void> response = balcaoController.deleteBalcao(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(204);

        verify(balcaoService).deleteBalcao(1L);
    }
}
