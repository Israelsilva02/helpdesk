package com.helpdesk.api.service;

import com.helpdesk.api.exception.BalcaoException;

import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.model.dto.BalcaoDTO;
import com.helpdesk.api.mapper.BalcaoMapper;
import com.helpdesk.api.repository.AtendenteBalcaoRepository;
import com.helpdesk.api.repository.BalcaoRepository;

import com.helpdesk.api.util.MessageConstants;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.helpdesk.api.mapper.BalcaoMapper.*;

@Service
@RequiredArgsConstructor
public class BalcaoServiceImpl {

    private final BalcaoRepository balcaoRepository;
    private final AtendenteBalcaoRepository atendenteBalcaoRepository;

    public BalcaoDTO createBalcaoAtendimento(BalcaoDTO balcaoDTO) {
        if (balcaoDTO.getIdAtendente() == null || balcaoRepository.findById(balcaoDTO.getIdAtendente()).isEmpty()) {
            return toDtoBalcaoDto(balcaoRepository.save(toEntityBalcao(balcaoDTO)));

        }
        throw new BalcaoException(MessageConstants.BALCAO_COM_ID_JA_EXISTE + balcaoDTO.getIdAtendente());
    }

    public List<BalcaoDTO> getAllBalcoes() {
        List<Balcao> balcoes = balcaoRepository.findAll();
        return toDtoBalcao(balcoes);
    }

    public Optional<BalcaoDTO> getBalcaoById(Long id) {
        return balcaoRepository.findById(id)
                .map(BalcaoMapper::toDtoBalcaoDto)
                .or(() -> {
                    throw new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_COM_ID + id);
                });
    }

    public Optional<BalcaoDTO> updateBalcao(Long id, BalcaoDTO updatedBalcaoDTO) {
        return balcaoRepository.findById(id)
                .map(existingBalcao -> {
                    Balcao updatedBalcao = toEntityBalcao(updatedBalcaoDTO);
                    updatedBalcao.setId(existingBalcao.getId());
                    Balcao savedBalcao = balcaoRepository.save(updatedBalcao);
                    return toDtoBalcaoDto(savedBalcao);
                }).or(() -> {
                    throw new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_COM_ID + id);
                });
    }

    public void deleteBalcao(Long id) {
        if (balcaoRepository.findById(id).isEmpty()) {
            throw new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_COM_ID + id);
        }
        balcaoRepository.deleteById(id);
    }
}
