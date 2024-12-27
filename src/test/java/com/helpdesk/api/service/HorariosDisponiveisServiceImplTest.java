package com.helpdesk.api.service;

import com.helpdesk.api.exception.HorariosDisponiveisException;
import com.helpdesk.api.mapper.HorariosDisponiveisMapper;
import com.helpdesk.api.model.HorariosDisponiveis;
import com.helpdesk.api.model.dto.HorariosDisponiveisDTO;
import com.helpdesk.api.repository.HorariosDisponiveisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HorariosDisponiveisServiceImplTest {

    @Mock
    private HorariosDisponiveisRepository horariosDisponiveisRepository;

    @Mock
    private HorariosDisponiveisMapper horariosDisponiveisMapper;

    @InjectMocks
    private HorariosDisponiveisServiceImpl horariosDisponiveisService;

    private HorariosDisponiveis horariosDisponiveis;
    private HorariosDisponiveisDTO horariosDisponiveisDTO;

    @BeforeEach
    void setUp() {
        horariosDisponiveis = new HorariosDisponiveis();
        horariosDisponiveisDTO = new HorariosDisponiveisDTO();
    }

    @Test
    void testFindAll() {
        List<HorariosDisponiveis> list = List.of(horariosDisponiveis);
        when(horariosDisponiveisRepository.findAll()).thenReturn(list);
        List<HorariosDisponiveisDTO> dtoList = List.of(horariosDisponiveisDTO);
        when(horariosDisponiveisMapper.toDTOList(list)).thenReturn(dtoList);

        List<HorariosDisponiveisDTO> result = horariosDisponiveisService.findAll();

        assertThat(result).isEqualTo(dtoList);
        verify(horariosDisponiveisRepository).findAll();
        verify(horariosDisponiveisMapper).toDTOList(list);
    }


    @Test
    void testCreateHorario_WhenHorarioIsNew() {
        when(horariosDisponiveisMapper.toEntity(horariosDisponiveisDTO)).thenReturn(horariosDisponiveis);
        when(horariosDisponiveisRepository.save(horariosDisponiveis)).thenReturn(horariosDisponiveis);
        when(horariosDisponiveisMapper.toDTO(horariosDisponiveis)).thenReturn(horariosDisponiveisDTO);

        HorariosDisponiveisDTO result = horariosDisponiveisService.createHorario(horariosDisponiveisDTO);

        verify(horariosDisponiveisRepository).save(horariosDisponiveis);
        assertThat(result).isEqualTo(horariosDisponiveisDTO);
    }
}
