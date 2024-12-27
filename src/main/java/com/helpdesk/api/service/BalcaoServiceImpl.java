package com.helpdesk.api.service;

import com.helpdesk.api.exception.BalcaoException;
import com.helpdesk.api.mapper.BalcaoMapper;
import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.model.dto.BalcaoDTO;
import com.helpdesk.api.model.dto.VisualizarBalcaoDTO;
import com.helpdesk.api.repository.BalcaoRepository;
import com.helpdesk.api.util.MessageConstants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BalcaoServiceImpl {

    private final BalcaoRepository balcaoRepository;
    private final BalcaoMapper balcaoMapper;

    public BalcaoDTO createBalcaoAtendimento(BalcaoDTO balcaoDTO) {
        if (balcaoDTO.getId() != null && balcaoRepository.existsById(balcaoDTO.getId())) {
            throw new BalcaoException(MessageConstants.BALCAO_COM_ID_JA_EXISTE + balcaoDTO.getId());
        }
        Balcao balcao = balcaoMapper.toEntity(balcaoDTO);
        Balcao savedBalcao = balcaoRepository.save(balcao);
        return balcaoMapper.toDTO(savedBalcao);
    }

    public List<BalcaoDTO> getAllBalcoes() {
        List<Balcao> balcoes = balcaoRepository.findAll();
        return balcaoMapper.toDTOList(balcoes);
    }

    public BalcaoDTO getBalcaoById(Long id) {
        Balcao balcao = balcaoRepository.findById(id)
                .orElseThrow(() -> new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_COM_ID + id));
        return balcaoMapper.toDTO(balcao);
    }

    public BalcaoDTO updateBalcao(Long id, BalcaoDTO updatedBalcaoDTO) {
        Balcao balcao = balcaoRepository.findById(id)
                .orElseThrow(() -> new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_COM_ID + id));
       balcaoMapper.toUpdate(balcao, updatedBalcaoDTO);
       balcaoRepository.save(balcao);
       return balcaoMapper.toDTO(balcao);
    }

    public void deleteBalcao(Long id) {
        if (!balcaoRepository.existsById(id)) {
            throw new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_COM_ID + id);
        }
        balcaoRepository.deleteById(id);
    }

    public Balcao getBalcao(Long id) {
        return balcaoRepository.findById(id)
                .orElseThrow(() -> new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_COM_ID + id));
    }

    public VisualizarBalcaoDTO getVisualizarBalcao(Long id) {
        Balcao balcao = getBalcao(id);
        return new VisualizarBalcaoDTO(balcao.getId(), balcao.getAtendente().getNome());

    }
}
