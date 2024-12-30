package com.helpdesk.api.mapper;

import com.helpdesk.api.model.Equipamento;
import com.helpdesk.api.model.dto.EquipamentoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EquipamentoMapperTest {

    private EquipamentoMapper equipamentoMapper;

    @BeforeEach
    public void setUp() {
        equipamentoMapper = Mappers.getMapper(EquipamentoMapper.class);
    }

    @Test
    public void testToDTO() {
        Equipamento equipamento = new Equipamento();
        equipamento.setDeviceId(1L);
        equipamento.setSerialNumber("123456789");

        EquipamentoDTO dto = equipamentoMapper.toDTO(equipamento);

        assertNotNull(dto);
        assertEquals(equipamento.getDeviceId(), dto.getDeviceId());
        assertEquals(equipamento.getSerialNumber(), dto.getSerialNumber());
    }

    @Test
    public void testToModel() {
        EquipamentoDTO equipamentoDTO = new EquipamentoDTO();
        equipamentoDTO.setDeviceId(1L);
        equipamentoDTO.setSerialNumber("123456789");

        Equipamento model = equipamentoMapper.toModel(equipamentoDTO);

        assertNotNull(model);
        assertEquals(equipamentoDTO.getDeviceId(), model.getDeviceId());
        assertEquals(equipamentoDTO.getSerialNumber(), model.getSerialNumber());
    }

    @Test
    public void testToDTOList() {
        Equipamento equipamento = new Equipamento();
        equipamento.setDeviceId(1L);
        equipamento.setSerialNumber("123456789");

        List<EquipamentoDTO> dtoList = equipamentoMapper.toDTOList(Collections.singletonList(equipamento));

        assertNotNull(dtoList);
        assertEquals(1, dtoList.size());
        assertEquals(equipamento.getDeviceId(), dtoList.get(0).getDeviceId());
    }

    @Test
    public void testToEntityList() {
        EquipamentoDTO equipamentoDTO = new EquipamentoDTO();
        equipamentoDTO.setDeviceId(1L);
        equipamentoDTO.setSerialNumber("123456789");

        List<Equipamento> entityList = equipamentoMapper.toEntity(Collections.singletonList(equipamentoDTO));

        assertNotNull(entityList);
        assertEquals(1, entityList.size());
        assertEquals(equipamentoDTO.getDeviceId(), entityList.get(0).getDeviceId());
    }

    @Test
    public void testToUpdate() {
        Equipamento equipamento = new Equipamento();
        equipamento.setDeviceId(1L);
        equipamento.setSerialNumber("987654321");

        EquipamentoDTO equipamentoDTO = new EquipamentoDTO();
        equipamentoDTO.setSerialNumber("12121212");

        equipamentoMapper.toUpdate(equipamento, equipamentoDTO);

        assertNotNull(equipamentoDTO.getSerialNumber());
        assertEquals(equipamento.getDeviceId(), equipamentoDTO.getDeviceId());
        assertEquals(equipamento.getSerialNumber(), equipamentoDTO.getSerialNumber());
    }
}
