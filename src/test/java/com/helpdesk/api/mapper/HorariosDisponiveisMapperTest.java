package com.helpdesk.api.mapper;

import com.helpdesk.api.model.HorariosDisponiveis;
import com.helpdesk.api.model.dto.HorariosDisponiveisDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HorariosDisponiveisMapperTest {

    private HorariosDisponiveisMapper horariosDisponiveisMapper;

    @BeforeEach
    public void setUp() {
        horariosDisponiveisMapper = Mappers.getMapper(HorariosDisponiveisMapper.class);
    }

    @Test
    public void testToDTO() {
        HorariosDisponiveis horariosDisponiveis = new HorariosDisponiveis();
        horariosDisponiveis.setId(1L);
        horariosDisponiveis.setHorariosDisponiveis(LocalDateTime.parse("2024-12-27T09:00:00"));

        HorariosDisponiveisDTO dto = horariosDisponiveisMapper.toDTO(horariosDisponiveis);

        assertNotNull(dto);
        assertEquals(horariosDisponiveis.getId(), dto.getId());
        assertEquals(horariosDisponiveis.getHorariosDisponiveis(), dto.getHorariosDisponiveis());
    }

    @Test
    public void testToEntity() {
        HorariosDisponiveisDTO dto = new HorariosDisponiveisDTO();
        dto.setId(1L);
        dto.setHorariosDisponiveis(LocalDateTime.parse("2024-12-27T09:00:00"));

        HorariosDisponiveis entity = horariosDisponiveisMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getHorariosDisponiveis(), entity.getHorariosDisponiveis());
    }

    @Test
    public void testToEntityList() {
        HorariosDisponiveisDTO dto = new HorariosDisponiveisDTO();
        dto.setId(1L);
        dto.setHorariosDisponiveis(LocalDateTime.parse("2024-12-27T09:00:00"));

        List<HorariosDisponiveis> entities = horariosDisponiveisMapper.toEntityList(Collections.singletonList(dto));

        assertNotNull(entities);
        assertEquals(1, entities.size());
        assertEquals(dto.getId(), entities.get(0).getId());
    }

    @Test
    public void testToDTOList() {
        HorariosDisponiveis horariosDisponiveis = new HorariosDisponiveis();
        horariosDisponiveis.setId(1L);
        horariosDisponiveis.setHorariosDisponiveis(LocalDateTime.parse("2024-12-27T09:00:00"));

        List<HorariosDisponiveisDTO> dtos = horariosDisponiveisMapper.toDTOList(Collections.singletonList(horariosDisponiveis));

        assertNotNull(dtos);
        assertEquals(1, dtos.size());
        assertEquals(horariosDisponiveis.getId(), dtos.get(0).getId());
    }
}
