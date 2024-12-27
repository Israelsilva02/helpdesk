package com.helpdesk.api.service;

import com.helpdesk.api.exception.AtendenteBalcaoException;
import com.helpdesk.api.mapper.AtendenteBalcaoMapper;
import com.helpdesk.api.model.AtendenteBalcao;
import com.helpdesk.api.model.dto.AtendenteBalcaoDTO;
import com.helpdesk.api.repository.AtendenteBalcaoRepository;
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
class AtendenteBalcaoServiceImplTest {

    @Mock
    private AtendenteBalcaoRepository atendenteBalcaoRepository;

    @Mock
    private AtendenteBalcaoMapper atendenteBalcaoMapper;

    @InjectMocks
    private AtendenteBalcaoServiceImpl atendenteBalcaoServiceImpl;

    private AtendenteBalcao atendenteBalcao;
    private AtendenteBalcaoDTO atendenteBalcaoDTO;

    @BeforeEach
    void setUp() {
        atendenteBalcao = new AtendenteBalcao();
        atendenteBalcaoDTO = new AtendenteBalcaoDTO();
    }

    @Test
    void testCreateAtendente() {
        when(atendenteBalcaoMapper.toEntity(atendenteBalcaoDTO)).thenReturn(atendenteBalcao);
        when(atendenteBalcaoRepository.save(atendenteBalcao)).thenReturn(atendenteBalcao);
        when(atendenteBalcaoMapper.toDTO(atendenteBalcao)).thenReturn(atendenteBalcaoDTO);

        AtendenteBalcaoDTO result = atendenteBalcaoServiceImpl.createAtendente(atendenteBalcaoDTO);

        verify(atendenteBalcaoRepository).save(atendenteBalcao);
        assertThat(result).isEqualTo(atendenteBalcaoDTO);
    }

    @Test
    void testGetAtendenteById() {
        Long id = 1L;
        when(atendenteBalcaoRepository.findById(id)).thenReturn(Optional.of(atendenteBalcao));
        when(atendenteBalcaoMapper.toDTO(atendenteBalcao)).thenReturn(atendenteBalcaoDTO);

        AtendenteBalcaoDTO result = atendenteBalcaoServiceImpl.getAtendenteById(id);

        assertThat(result).isEqualTo(atendenteBalcaoDTO);
    }

    @Test
    void testGetAtendenteById_NotFound() {
        Long id = 1L;
        when(atendenteBalcaoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(AtendenteBalcaoException.class, () -> atendenteBalcaoServiceImpl.getAtendenteById(id));
    }

    @Test
    void testGetAllAtendentes() {
        List<AtendenteBalcao> atendentes = List.of(atendenteBalcao);
        when(atendenteBalcaoRepository.findAll()).thenReturn(atendentes);
        List<AtendenteBalcaoDTO> atendenteBalcaoDTOs = List.of(atendenteBalcaoDTO);
        when(atendenteBalcaoMapper.toDTOList(atendentes)).thenReturn(atendenteBalcaoDTOs);

        List<AtendenteBalcaoDTO> result = atendenteBalcaoServiceImpl.getAllAtendentes();

        assertThat(result).isEqualTo(atendenteBalcaoDTOs);
    }

    @Test
    void testUpdateAtendente() {
        Long id = 1L;
        when(atendenteBalcaoRepository.findById(id)).thenReturn(Optional.of(atendenteBalcao));
        doNothing().when(atendenteBalcaoMapper).toUpdate(atendenteBalcao, atendenteBalcaoDTO);
        when(atendenteBalcaoRepository.save(atendenteBalcao)).thenReturn(atendenteBalcao);
        when(atendenteBalcaoMapper.toDTO(atendenteBalcao)).thenReturn(atendenteBalcaoDTO);

        AtendenteBalcaoDTO result = atendenteBalcaoServiceImpl.updateAtendente(id, atendenteBalcaoDTO);

        verify(atendenteBalcaoRepository).save(atendenteBalcao);
        assertThat(result).isEqualTo(atendenteBalcaoDTO);
    }

    @Test
    void testDeleteAtendente() {
        Long id = 1L;
        when(atendenteBalcaoRepository.existsById(id)).thenReturn(true);

        atendenteBalcaoServiceImpl.deleteAtendente(id);

        verify(atendenteBalcaoRepository).deleteById(id);
    }

    @Test
    void testDeleteAtendente_NotFound() {
        Long id = 1L;
        when(atendenteBalcaoRepository.existsById(id)).thenReturn(false);

        assertThrows(AtendenteBalcaoException.class, () -> atendenteBalcaoServiceImpl.deleteAtendente(id));
    }
}
