package com.helpdesk.api.mapper;

import com.helpdesk.api.model.AtendenteBalcao;
import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.model.dto.BalcaoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BalcaoMapperTest {

    private BalcaoMapper balcaoMapper;

    @BeforeEach
    public void setUp() {
        balcaoMapper = Mappers.getMapper(BalcaoMapper.class);
    }

    @Test
    public void testToDTO() {
        AtendenteBalcao atendente = new AtendenteBalcao();
        atendente.setId(100L);

        Balcao balcao = new Balcao();
        balcao.setId(1L);
        balcao.setAtendente(atendente);

        BalcaoDTO dto = balcaoMapper.toDTO(balcao);

        assertNotNull(dto);
        assertEquals(balcao.getId(), dto.getId());
        assertEquals(atendente.getId(), dto.getAtendente());
    }

    @Test
    public void testToEntity() {
        BalcaoDTO balcaoDTO = new BalcaoDTO();
        balcaoDTO.setId(1L);

        Balcao balcao = balcaoMapper.toEntity(balcaoDTO);

        assertNotNull(balcao);
        assertEquals(balcaoDTO.getId(), balcao.getId());
        assertNull(balcao.getAtendente());
    }

    @Test
    public void testToDTOList() {
        Balcao balcao = new Balcao();
        balcao.setId(1L);
        balcao.setAtendente(new AtendenteBalcao());

        List<BalcaoDTO> dtoList = balcaoMapper.toDTOList(Collections.singletonList(balcao));

        assertNotNull(dtoList);
        assertEquals(1, dtoList.size());
        assertEquals(balcao.getId(), dtoList.get(0).getId());
    }

    @Test
    public void testToEntityList() {
        BalcaoDTO balcaoDTO = new BalcaoDTO();
        balcaoDTO.setId(1L);

        List<Balcao> entityList = balcaoMapper.toEntityList(Collections.singletonList(balcaoDTO));

        assertNotNull(entityList);
        assertEquals(1, entityList.size());
        assertEquals(balcaoDTO.getId(), entityList.get(0).getId());
        assertNull(entityList.get(0).getAtendente());
    }

    @Test
    public void testToUpdate() {
        Balcao balcao = new Balcao();
        balcao.setId(1L);
        balcao.setAtendente(new AtendenteBalcao());

        BalcaoDTO balcaoDTO = new BalcaoDTO();
        balcaoDTO.setId(1L);

        balcaoMapper.toUpdate(balcao, balcaoDTO);

        assertEquals(balcaoDTO.getId(), balcao.getId());
        assertNotNull(balcao.getAtendente());
    }
}
