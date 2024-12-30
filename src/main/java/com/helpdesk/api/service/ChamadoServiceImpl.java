package com.helpdesk.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.helpdesk.api.exception.ChamadoException;
import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.enums.EstadoChamado;
import com.helpdesk.api.model.dto.BalcaoDTO;
import com.helpdesk.api.model.dto.ChamadoDTO;
import com.helpdesk.api.mapper.ChamadoMapper;
import com.helpdesk.api.model.dto.VisualizarBalcaoDTO;
import com.helpdesk.api.model.dto.VisualizarChamadoDTO;
import com.helpdesk.api.producer.ChamadoRequestProducer;
import com.helpdesk.api.repository.BalcaoRepository;
import com.helpdesk.api.repository.ChamadoRepository;

import com.helpdesk.api.util.MessageConstants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.helpdesk.api.mapper.ChamadoMapper.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ChamadoServiceImpl {

    private final ChamadoRepository chamadoRepository;
    private final ChamadoMapper chamadoMapper;
    private final BalcaoServiceImpl balcaoService;
    private final ChamadoRequestProducer chamadoRequestProducer;

    public VisualizarChamadoDTO createChamado(ChamadoDTO chamadoDTO) {
        try {
            chamadoRequestProducer.integrar(chamadoDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(MessageConstants.ERRO_AO_ABRIR_CHAMADO);
        }
        VisualizarBalcaoDTO visualizarBalcaoDTO = balcaoService.getVisualizarBalcao(chamadoDTO.getBalcao());
        Chamado chamado = chamadoMapper.toEntity(chamadoDTO);
        Chamado savedChamado = chamadoRepository.save(chamado);
        return chamadoMapper.toDTOVisualizar(savedChamado, visualizarBalcaoDTO);
    }

//    public String abrirChamado(ChamadoDTO chamadoDTO) {
//        try {
//            chamadoRequestProducer.integrar(chamadoDTO);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(MessageConstants.ERRO_AO_ABRIR_CHAMADO);
//        }
//        return "Abrindo chamado ....";
//    }

    public List<ChamadoDTO> getAllChamados() {
        List<Chamado> chamados = chamadoRepository.findAll();
        return chamadoMapper.toDTOList(chamados);
    }

    public ChamadoDTO getChamadoById(Long id) {
        Chamado chamado = chamadoRepository.findById(id)
                .orElseThrow(() -> new ChamadoException(MessageConstants.USUARIO_NAO_ENCONTRADO_COM_ID + id));
        return chamadoMapper.toDTO(chamado);
    }

    public List<ChamadoDTO> getChamadosPorEstado(EstadoChamado estado) {
        List<Chamado> chamados = chamadoRepository.findByEstadoChamado(estado);
        return chamadoMapper.toDTOList(chamados);
    }

    public ChamadoDTO updateChamado(Long id, ChamadoDTO chamadoDTO) {
        Chamado chamado = chamadoRepository.findById(id)
                .orElseThrow(() -> new ChamadoException(MessageConstants.USUARIO_NAO_ENCONTRADO_COM_ID + id));
        chamadoMapper.toUpdate(chamado, chamadoDTO);
        chamadoRepository.save(chamado);
        return chamadoMapper.toDTO(chamado);
    }

    public void deleteChamado(Long id) {
        if (!chamadoRepository.existsById(id)) {
            throw new RuntimeException(MessageConstants.CHAMADO_NAO_ENCONTRADO_COM_ID + id);
        }
        chamadoRepository.deleteById(id);
    }

    public void sucessoChamado(String payload) {
        System.out.println("====SUCESSO AO ABRIR CHAMADO=====" + payload);
    }
    public void erroChamado(String payload) {
        System.err.println("====ERRO AO TENTAR ABRIR CHAMADO====" + payload);

    }

}
