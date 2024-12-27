package com.helpdesk.api.service;

import com.helpdesk.api.exception.EquipamentoException;
import com.helpdesk.api.mapper.EquipamentoMapper;
import com.helpdesk.api.model.Equipamento;
import com.helpdesk.api.model.dto.EquipamentoDTO;
import com.helpdesk.api.repository.EquipamentoRepository;
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
class EquipamentoServiceImplTest {

    @Mock
    private EquipamentoRepository equipamentoRepository;

    @Mock
    private EquipamentoMapper equipamentoMapper;

    @InjectMocks
    private EquipamentoServiceImpl equipamentoService;

    private Equipamento equipamento;
    private EquipamentoDTO equipamentoDTO;

    @BeforeEach
    void setUp() {
        equipamento = new Equipamento();
        equipamentoDTO = new EquipamentoDTO();
    }

    @Test
    void testSave() {
        when(equipamentoMapper.toModel(equipamentoDTO)).thenReturn(equipamento);
        when(equipamentoRepository.save(equipamento)).thenReturn(equipamento);
        when(equipamentoMapper.toDTO(equipamento)).thenReturn(equipamentoDTO);

        EquipamentoDTO result = equipamentoService.save(equipamentoDTO);

        verify(equipamentoRepository).save(equipamento);
        assertThat(result).isEqualTo(equipamentoDTO);
    }

    @Test
    void testFindAll() {
        List<Equipamento> equipamentos = List.of(equipamento);
        when(equipamentoRepository.findAll()).thenReturn(equipamentos);
        List<EquipamentoDTO> equipamentoDTOs = List.of(equipamentoDTO);
        when(equipamentoMapper.toDTOList(equipamentos)).thenReturn(equipamentoDTOs);

        List<EquipamentoDTO> result = equipamentoService.findAll();

        assertThat(result).isEqualTo(equipamentoDTOs);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        when(equipamentoRepository.findById(id)).thenReturn(Optional.of(equipamento));
        when(equipamentoMapper.toDTO(equipamento)).thenReturn(equipamentoDTO);

        EquipamentoDTO result = equipamentoService.findById(id);

        verify(equipamentoRepository).findById(id);
        assertThat(result).isEqualTo(equipamentoDTO);
    }

    @Test
    void testFindById_NotFound() {
        Long id = 1L;
        when(equipamentoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EquipamentoException.class, () -> equipamentoService.findById(id));

        verify(equipamentoRepository).findById(id);
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        when(equipamentoRepository.findById(id)).thenReturn(Optional.of(equipamento));
        doNothing().when(equipamentoMapper).toUpdate(equipamento, equipamentoDTO);
        when(equipamentoRepository.save(equipamento)).thenReturn(equipamento);
        when(equipamentoMapper.toDTO(equipamento)).thenReturn(equipamentoDTO);

        EquipamentoDTO result = equipamentoService.update(id, equipamentoDTO);

        verify(equipamentoRepository).save(equipamento);
        assertThat(result).isEqualTo(equipamentoDTO);
    }

    @Test
    void testUpdate_NotFound() {
        Long id = 1L;
        when(equipamentoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EquipamentoException.class, () -> equipamentoService.update(id, equipamentoDTO));

        verify(equipamentoRepository).findById(id);
    }

    @Test
    void testDelete() {
        Long id = 1L;
        when(equipamentoRepository.existsById(id)).thenReturn(true);

        equipamentoService.delete(id);

        verify(equipamentoRepository).deleteById(id);
    }

    @Test
    void testDelete_NotFound() {
        Long id = 1L;
        when(equipamentoRepository.existsById(id)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> equipamentoService.delete(id));

        verify(equipamentoRepository).existsById(id);
    }
}
