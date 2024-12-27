package com.helpdesk.api.service;

import com.helpdesk.api.exception.BalcaoException;
import com.helpdesk.api.mapper.BalcaoMapper;
import com.helpdesk.api.model.AtendenteBalcao;
import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.model.dto.BalcaoDTO;
import com.helpdesk.api.model.dto.VisualizarBalcaoDTO;
import com.helpdesk.api.repository.BalcaoRepository;
import com.helpdesk.api.util.MessageConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BalcaoServiceImplTest {

    @Mock
    private BalcaoRepository balcaoRepository;

    @Mock
    private BalcaoMapper balcaoMapper;

    @InjectMocks
    private BalcaoServiceImpl balcaoServiceImpl;

    private Balcao balcao;
    private BalcaoDTO balcaoDTO;

    @BeforeEach
    void setUp() {
        balcao = new Balcao();
        balcaoDTO = new BalcaoDTO();
    }

    @Test
    void testCreateBalcaoAtendimento() {
        when(balcaoMapper.toEntity(balcaoDTO)).thenReturn(balcao);
        when(balcaoRepository.save(balcao)).thenReturn(balcao);
        when(balcaoMapper.toDTO(balcao)).thenReturn(balcaoDTO);

        BalcaoDTO createdBalcao = balcaoServiceImpl.createBalcaoAtendimento(balcaoDTO);

        verify(balcaoRepository).save(balcao);
        assertThat(createdBalcao).isEqualTo(balcaoDTO);
    }

    @Test
    void testGetAllBalcoes() {
        List<Balcao> balcoes = List.of(balcao);
        when(balcaoRepository.findAll()).thenReturn(balcoes);
        List<BalcaoDTO> balcaoDTOs = List.of(balcaoDTO);
        when(balcaoMapper.toDTOList(balcoes)).thenReturn(balcaoDTOs);

        List<BalcaoDTO> result = balcaoServiceImpl.getAllBalcoes();

        assertThat(result).isEqualTo(balcaoDTOs);
    }

    @Test
    void testGetBalcaoById() {
        Long id = 1L;
        when(balcaoRepository.findById(id)).thenReturn(Optional.of(balcao));
        when(balcaoMapper.toDTO(balcao)).thenReturn(balcaoDTO);

        BalcaoDTO result = balcaoServiceImpl.getBalcaoById(id);

        assertThat(result).isEqualTo(balcaoDTO);
    }

    @Test
    void testGetBalcaoById_NotFound() {
        Long id = 1L;
        when(balcaoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(BalcaoException.class, () -> balcaoServiceImpl.getBalcaoById(id));
    }

    @Test
    void testUpdateBalcao() {
        Long id = 1L;
        when(balcaoRepository.findById(id)).thenReturn(Optional.of(balcao));
        balcaoMapper.toUpdate(balcao, balcaoDTO);
        when(balcaoRepository.save(balcao)).thenReturn(balcao);
        when(balcaoMapper.toDTO(balcao)).thenReturn(balcaoDTO);

        BalcaoDTO result = balcaoServiceImpl.updateBalcao(id, balcaoDTO);

        verify(balcaoRepository).save(balcao);
        assertThat(result).isEqualTo(balcaoDTO);
    }

    @Test
    void testDeleteBalcao() {
        Long id = 1L;
        when(balcaoRepository.existsById(id)).thenReturn(true);

        balcaoServiceImpl.deleteBalcao(id);

        verify(balcaoRepository).deleteById(id);
    }

    @Test
    void testDeleteBalcao_NotFound() {
        Long id = 1L;
        when(balcaoRepository.existsById(id)).thenReturn(false);

        assertThrows(BalcaoException.class, () -> balcaoServiceImpl.deleteBalcao(id));
    }

    @Test
    void testGetVisualizarBalcao() {
        AtendenteBalcao atendenteBalcao = new AtendenteBalcao();
        atendenteBalcao.setId(1L);
        atendenteBalcao.setNome("israel");

        Balcao balcao = new Balcao();
        balcao.setId(1L);
        balcao.setAtendente(atendenteBalcao);

        Long id = 1L;
        when(balcaoRepository.findById(id)).thenReturn(Optional.of(balcao));
        VisualizarBalcaoDTO visualizarBalcaoDTO = new VisualizarBalcaoDTO(balcao.getId(), balcao.getAtendente().getNome());
        VisualizarBalcaoDTO result = balcaoServiceImpl.getVisualizarBalcao(id);
        assertThat(result).isEqualTo(visualizarBalcaoDTO);
    }
}
