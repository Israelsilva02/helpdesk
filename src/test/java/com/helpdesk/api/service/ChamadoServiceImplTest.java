package com.helpdesk.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.helpdesk.api.exception.ChamadoException;
import com.helpdesk.api.mapper.ChamadoMapper;
import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.enums.EstadoChamado;
import com.helpdesk.api.model.dto.ChamadoDTO;
import com.helpdesk.api.model.dto.VisualizarBalcaoDTO;
import com.helpdesk.api.model.dto.VisualizarChamadoDTO;
import com.helpdesk.api.producer.ChamadoRequestProducer;
import com.helpdesk.api.repository.ChamadoRepository;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChamadoServiceImplTest {

    @Mock
    private ChamadoRequestProducer chamadoRequestProducer;

    @Mock
    private VisualizarBalcaoDTO visualizarBalcaoDTO;
    @Mock
    private ChamadoRepository chamadoRepository;

    @Mock
    private ChamadoMapper chamadoMapper;

    @Mock
    private BalcaoServiceImpl balcaoService;

    @InjectMocks
    private ChamadoServiceImpl chamadoService;

    private Chamado chamado;
    private ChamadoDTO chamadoDTO;
    private VisualizarChamadoDTO visualizarChamadoDTO;

    @BeforeEach
    void setUp() {
        chamado = new Chamado();
        chamadoDTO = new ChamadoDTO();
        visualizarChamadoDTO = new VisualizarChamadoDTO();
    }

    @Test
    void testCreateChamado() throws JsonProcessingException {
        doNothing().when(chamadoRequestProducer).integrar(any(ChamadoDTO.class));
        when(balcaoService.getVisualizarBalcao(chamadoDTO.getBalcao())).thenReturn(visualizarBalcaoDTO);
        when(chamadoMapper.toEntity(chamadoDTO)).thenReturn(chamado);
        when(chamadoRepository.save(chamado)).thenReturn(chamado);
        when(chamadoMapper.toDTOVisualizar(chamado, visualizarBalcaoDTO)).thenReturn(visualizarChamadoDTO);


        VisualizarChamadoDTO result = chamadoService.createChamado(chamadoDTO);


        assertEquals(visualizarChamadoDTO, result);
        verify(chamadoRequestProducer).integrar(chamadoDTO);
        verify(balcaoService).getVisualizarBalcao(chamadoDTO.getBalcao());
        verify(chamadoMapper).toEntity(chamadoDTO);
        verify(chamadoRepository).save(chamado);
        verify(chamadoMapper).toDTOVisualizar(chamado, visualizarBalcaoDTO);
    }

    @Test
    void testGetAllChamados() {
        List<Chamado> chamados = List.of(chamado);
        when(chamadoRepository.findAll()).thenReturn(chamados);
        List<ChamadoDTO> chamadoDTOs = List.of(chamadoDTO);
        when(chamadoMapper.toDTOList(chamados)).thenReturn(chamadoDTOs);

        List<ChamadoDTO> result = chamadoService.getAllChamados();

        assertThat(result).isEqualTo(chamadoDTOs);
    }

    @Test
    void testGetChamadoById() {
        Long id = 1L;
        when(chamadoRepository.findById(id)).thenReturn(Optional.of(chamado));
        when(chamadoMapper.toDTO(chamado)).thenReturn(chamadoDTO);

        ChamadoDTO result = chamadoService.getChamadoById(id);

        verify(chamadoRepository).findById(id);
        assertThat(result).isEqualTo(chamadoDTO);
    }

    @Test
    void testGetChamadoById_NotFound() {
        Long id = 1L;
        when(chamadoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ChamadoException.class, () -> chamadoService.getChamadoById(id));

        verify(chamadoRepository).findById(id);
    }

    @Test
    void testGetChamadosPorEstado() {
        EstadoChamado estado = EstadoChamado.ABERTO;
        List<Chamado> chamados = List.of(chamado);
        when(chamadoRepository.findByEstadoChamado(estado)).thenReturn(chamados);
        List<ChamadoDTO> chamadoDTOs = List.of(chamadoDTO);
        when(chamadoMapper.toDTOList(chamados)).thenReturn(chamadoDTOs);

        List<ChamadoDTO> result = chamadoService.getChamadosPorEstado(estado);

        assertThat(result).isEqualTo(chamadoDTOs);
    }

    @Test
    void testUpdateChamado() {
        Long id = 1L;
        when(chamadoRepository.findById(id)).thenReturn(Optional.of(chamado));
        doNothing().when(chamadoMapper).toUpdate(chamado, chamadoDTO);
        when(chamadoRepository.save(chamado)).thenReturn(chamado);
        when(chamadoMapper.toDTO(chamado)).thenReturn(chamadoDTO);

        ChamadoDTO result = chamadoService.updateChamado(id, chamadoDTO);

        verify(chamadoRepository).save(chamado);
        assertThat(result).isEqualTo(chamadoDTO);
    }

    @Test
    void testUpdateChamado_NotFound() {
        Long id = 1L;
        when(chamadoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ChamadoException.class, () -> chamadoService.updateChamado(id, chamadoDTO));

        verify(chamadoRepository).findById(id);
    }

    @Test
    void testDeleteChamado() {
        Long id = 1L;
        when(chamadoRepository.existsById(id)).thenReturn(true);

        chamadoService.deleteChamado(id);

        verify(chamadoRepository).deleteById(id);
    }

    @Test
    void testDeleteChamado_NotFound() {
        Long id = 1L;
        when(chamadoRepository.existsById(id)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> chamadoService.deleteChamado(id));

        verify(chamadoRepository).existsById(id);
    }
}
