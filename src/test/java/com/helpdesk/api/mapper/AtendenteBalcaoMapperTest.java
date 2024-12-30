package com.helpdesk.api.mapper;

import com.helpdesk.api.model.AtendenteBalcao;
import com.helpdesk.api.model.dto.AtendenteBalcaoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AtendenteBalcaoMapperTest {

    private AtendenteBalcaoMapper atendenteBalcaoMapper;

    @BeforeEach
    public void setUp() {
        atendenteBalcaoMapper = Mappers.getMapper(AtendenteBalcaoMapper.class);
    }

    @Test
    public void testToDTO() {
        AtendenteBalcao atendenteBalcao = new AtendenteBalcao();
        atendenteBalcao.setId(1L);
        atendenteBalcao.setNome("israel");

        AtendenteBalcaoDTO dto = atendenteBalcaoMapper.toDTO(atendenteBalcao);

        assertNotNull(dto);
        assertEquals(atendenteBalcao.getId(), dto.getId());
        assertEquals(atendenteBalcao.getNome(), dto.getNome());
    }

    @Test
    public void testToEntity() {
        AtendenteBalcaoDTO dto = new AtendenteBalcaoDTO();
        dto.setId(1L);
        dto.setNome("israel");

        AtendenteBalcao entity = atendenteBalcaoMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getNome(), entity.getNome());
    }

    @Test
    public void testToDTOList() {
        AtendenteBalcao atendenteBalcao = new AtendenteBalcao();
        atendenteBalcao.setId(1L);
        atendenteBalcao.setNome("israel");

        List<AtendenteBalcaoDTO> dtoList = atendenteBalcaoMapper.toDTOList(Collections.singletonList(atendenteBalcao));

        assertNotNull(dtoList);
        assertEquals(1, dtoList.size());
        assertEquals(atendenteBalcao.getId(), dtoList.get(0).getId());
    }

    @Test
    public void testToEntityList() {
        AtendenteBalcaoDTO dto = new AtendenteBalcaoDTO();
        dto.setId(1L);
        dto.setNome("israel");

        List<AtendenteBalcao> entityList = atendenteBalcaoMapper.toEntityList(Collections.singletonList(dto));

        assertNotNull(entityList);
        assertEquals(1, entityList.size());
        assertEquals(dto.getId(), entityList.get(0).getId());
    }

    @Test
    public void testToUpdate() {
        AtendenteBalcao atendenteBalcao = new AtendenteBalcao();
        atendenteBalcao.setId(1L);
        atendenteBalcao.setNome("israel");

        AtendenteBalcaoDTO dto = new AtendenteBalcaoDTO();
        dto.setNome("israel bernardo");

        atendenteBalcaoMapper.toUpdate(atendenteBalcao, dto);

        assertEquals("israel bernardo", atendenteBalcao.getNome());
        assertEquals(atendenteBalcao.getId(), dto.getId());
    }
}
