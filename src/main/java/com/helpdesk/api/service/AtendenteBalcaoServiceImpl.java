package com.helpdesk.api.service;

import com.helpdesk.api.exception.AtendenteBalcaoException;
import com.helpdesk.api.mapper.AtendenteBalcaoMapper;
import com.helpdesk.api.model.AtendenteBalcao;
import com.helpdesk.api.model.dto.AtendenteBalcaoDTO;
import com.helpdesk.api.repository.AtendenteBalcaoRepository;
import com.helpdesk.api.util.MessageConstants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AtendenteBalcaoServiceImpl {

    private final AtendenteBalcaoRepository atendenteBalcaoRepository;
    private final AtendenteBalcaoMapper atendenteBalcaoMapper;

    public AtendenteBalcaoDTO createAtendente(AtendenteBalcaoDTO atendenteBalcaoDTO) {
        AtendenteBalcao atendenteBalcao = atendenteBalcaoMapper.toEntity(atendenteBalcaoDTO);
        AtendenteBalcao atendenteBalcaoSave = atendenteBalcaoRepository.save(atendenteBalcao);
        return atendenteBalcaoMapper.toDTO(atendenteBalcaoSave);
    }

    public AtendenteBalcaoDTO getAtendenteById(Long id) {
        AtendenteBalcao atendenteBalcao = atendenteBalcaoRepository.findById(id)
                .orElseThrow(() -> new AtendenteBalcaoException(MessageConstants.ATENDENTE_COM_O_ID_NAO_EXISTE + id));
        return atendenteBalcaoMapper.toDTO(atendenteBalcao);
    }

    public List<AtendenteBalcaoDTO> getAllAtendentes() {
        List<AtendenteBalcao> atendentes = atendenteBalcaoRepository.findAll();
        return atendenteBalcaoMapper.toDTOList(atendentes);
    }

    public AtendenteBalcaoDTO updateAtendente(Long id, AtendenteBalcaoDTO atendenteDTO) {
        AtendenteBalcao atendente = atendenteBalcaoRepository.findById(id)
                .orElseThrow(() -> new AtendenteBalcaoException(MessageConstants.ATENDENTE_COM_O_ID_NAO_EXISTE + id));
        atendenteBalcaoMapper.toUpdate(atendente, atendenteDTO);
        atendenteBalcaoRepository.save(atendente);
        return atendenteBalcaoMapper.toDTO(atendente);
    }

    public void deleteAtendente(Long id) {
        if (!atendenteBalcaoRepository.existsById(id)) {
            throw new AtendenteBalcaoException(MessageConstants.ATENDENTE_COM_O_ID_NAO_EXISTE + id);
        }
        atendenteBalcaoRepository.deleteById(id);
    }
}
