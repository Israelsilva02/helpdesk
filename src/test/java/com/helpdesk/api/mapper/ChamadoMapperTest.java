package com.helpdesk.api.mapper;

import com.helpdesk.api.enums.EstadoChamado;

import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.dto.ChamadoDTO;
import com.helpdesk.api.model.dto.VisualizarBalcaoDTO;
import com.helpdesk.api.model.dto.VisualizarChamadoDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChamadoMapperTest {

    private ChamadoMapper chamadoMapper;

    @BeforeEach
    public void setUp() {
        chamadoMapper = Mappers.getMapper(ChamadoMapper.class);
    }

    @Test
    public void testToDTO() {
        Chamado chamado = new Chamado();
        chamado.setId(1L);
        chamado.setMotivoChamado("Chamado para verificar Macbook pro 300");
        chamado.setEstadoChamado(EstadoChamado.ABERTO);

        ChamadoDTO dto = chamadoMapper.toDTO(chamado);

        assertNotNull(dto);
        assertEquals(chamado.getId(), dto.getId());
        assertEquals(chamado.getMotivoChamado(), dto.getMotivoChamado());
    }

    @Test
    public void testToEntity() {
        ChamadoDTO chamadoDTO = new ChamadoDTO();
        chamadoDTO.setId(1L);
        chamadoDTO.setMotivoChamado("Chamado para verificar Macbook pro 300");

        Chamado entity = chamadoMapper.toEntity(chamadoDTO);

        assertNotNull(entity);
        assertEquals(chamadoDTO.getId(), entity.getId());
        assertEquals(chamadoDTO.getMotivoChamado(), entity.getMotivoChamado());
        assertEquals(EstadoChamado.ABERTO, entity.getEstadoChamado());
    }

    @Test
    public void testToDTOList() {
        Chamado chamado = new Chamado();
        chamado.setId(1L);

        List<ChamadoDTO> dtoList = chamadoMapper.toDTOList(Collections.singletonList(chamado));

        assertNotNull(dtoList);
        assertEquals(1, dtoList.size());
        assertEquals(chamado.getId(), dtoList.get(0).getId());
    }

    @Test
    public void testToEntityList() {
        ChamadoDTO chamadoDTO = new ChamadoDTO();
        chamadoDTO.setId(1L);

        List<Chamado> entityList = chamadoMapper.toEntityList(Collections.singletonList(chamadoDTO));

        assertNotNull(entityList);
        assertEquals(1, entityList.size());
        assertEquals(chamadoDTO.getId(), entityList.get(0).getId());
    }

    @Test
    public void testToUpdate() {
        Chamado chamado = new Chamado();
        chamado.setId(1L);
        chamado.setMotivoChamado("Chamado inicial");

        ChamadoDTO chamadoDTO = new ChamadoDTO();
        chamadoDTO.setMotivoChamado("Atualizando o status do chamado");

        chamadoMapper.toUpdate(chamado, chamadoDTO);
        assertEquals(chamado.getId(), chamadoDTO.getId());
        assertEquals(chamado.getMotivoChamado(), chamadoDTO.getMotivoChamado());
    }

    @Test
    public void testToDTOVisualizar() {
        Chamado chamado = new Chamado();
        chamado.setId(1L);
        chamado.setMotivoChamado("General Inquiry");
        chamado.setDataChamado(LocalDateTime.now());
        chamado.setDataResolucao(LocalDateTime.now().plusDays(3));
        chamado.setEstadoChamado(EstadoChamado.CONCLUIDO);

        VisualizarBalcaoDTO visualizarBalcaoDTO = new VisualizarBalcaoDTO();
        visualizarBalcaoDTO.setId(10L);
        visualizarBalcaoDTO.setAtendente("israel bernardo");

        VisualizarChamadoDTO visualizarChamadoDTO = chamadoMapper.toDTOVisualizar(chamado, visualizarBalcaoDTO);

        assertNotNull(visualizarChamadoDTO);
        assertEquals(chamado.getId(), visualizarChamadoDTO.getId());
        assertEquals(chamado.getMotivoChamado(), visualizarChamadoDTO.getMotivoChamado());
        assertEquals(chamado.getEstadoChamado(), visualizarChamadoDTO.getEstadoChamado());
        assertEquals(visualizarBalcaoDTO.getId(), visualizarChamadoDTO.getBalcao().getId());
        assertEquals(visualizarBalcaoDTO.getAtendente(), visualizarChamadoDTO.getBalcao().getAtendente());
    }
}
