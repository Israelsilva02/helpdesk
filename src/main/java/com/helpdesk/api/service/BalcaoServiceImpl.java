package com.helpdesk.api.service;

import com.helpdesk.api.exception.BalcaoException;
import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.model.dto.BalcaoDTO;
import com.helpdesk.api.repository.BalcaoRepository;
import com.helpdesk.api.util.MessageConstants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.helpdesk.api.mapper.BalcaoMapper.*;

@Service
@RequiredArgsConstructor
@Transactional
public class BalcaoServiceImpl {

    private final BalcaoRepository balcaoRepository;

    public BalcaoDTO createBalcaoAtendimento(BalcaoDTO balcaoDTO) {
        if (balcaoDTO.getId() != null && balcaoRepository.existsById(balcaoDTO.getId())) {
            throw new BalcaoException(MessageConstants.BALCAO_COM_ID_JA_EXISTE + balcaoDTO.getId());
        }
        Balcao balcao = INSTANCE.toEntity(balcaoDTO);
        Balcao savedBalcao = balcaoRepository.save(balcao);
        return INSTANCE.toDTO(savedBalcao);
    }
    public List<BalcaoDTO> getAllBalcoes() {
        List<Balcao> balcoes = balcaoRepository.findAll();
        return INSTANCE.toDTOList(balcoes);
    }
    public BalcaoDTO getBalcaoById(Long id) {
        Balcao balcao = balcaoRepository.findById(id)
                .orElseThrow(() -> new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_COM_ID + id));
        return INSTANCE.toDTO(balcao);
    }
    public BalcaoDTO updateBalcao(Long id, BalcaoDTO updatedBalcaoDTO) {
        Balcao existingBalcao = balcaoRepository.findById(id)
                .orElseThrow(() -> new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_COM_ID + id));
        Balcao updatedBalcao = INSTANCE.toEntity(updatedBalcaoDTO);
        updatedBalcao.setId(existingBalcao.getId());
        updatedBalcao.setAtendente(updatedBalcao.getAtendente());

        Balcao savedBalcao = balcaoRepository.save(updatedBalcao);
        return INSTANCE.toDTO(savedBalcao);
    }
    public void deleteBalcao(Long id) {
        if (!balcaoRepository.existsById(id)) {
            throw new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_COM_ID + id);
        }
        balcaoRepository.deleteById(id);
    }

}
