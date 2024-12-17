package com.helpdesk.api.service;

import com.helpdesk.api.exception.BalcaoException;
import com.helpdesk.api.model.AtendenteBalcao;
import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.model.dto.BalcaoDTO;
import com.helpdesk.api.mapper.BalcaoMapper;
import com.helpdesk.api.repository.AtendenteBalcaoRepository;
import com.helpdesk.api.repository.BalcaoRepository;
import com.helpdesk.api.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BalcaoServiceImpl {

    private final BalcaoRepository balcaoRepository;

    private final AtendenteBalcaoRepository atendenteBalcaoRepository;

    public BalcaoDTO createBalcaoAtendimento(BalcaoDTO balcaoDTO) {
        AtendenteBalcao atendente;
        if (balcaoDTO.getAtendente() == null || balcaoDTO.getAtendente().getId() == null) {
            atendente = new AtendenteBalcao();
            atendente.setNome(balcaoDTO.getAtendente().getNome());
            atendente = atendenteBalcaoRepository.save(atendente);
        } else {
            atendente = atendenteBalcaoRepository.findById(balcaoDTO.getAtendente().getId())
                    .orElseThrow(() -> new BalcaoException(MessageConstants.ATENDENTE_COM_O_ID_NAO_EXISTE));
        }
        if (balcaoRepository.existsById(balcaoDTO.getId())) {
            throw new BalcaoException(MessageConstants.BALCAO_COM_ID_JA_EXISTE + balcaoDTO.getId());
        }

        Balcao balcao = BalcaoMapper.toEntityBalcao(balcaoDTO);
        balcao.setAtendente(atendente);
        Balcao savedBalcao = balcaoRepository.save(balcao);
        return BalcaoMapper.toDtoBalcaoDto(savedBalcao);
    }

    public List<BalcaoDTO> getAllBalcoes() {
        List<Balcao> balcoes = balcaoRepository.findAll();
        return BalcaoMapper.toDtoBalcao(balcoes);
    }

    public BalcaoDTO getBalcaoById(Long id) {
        return balcaoRepository.findById(id)
                .map(BalcaoMapper::toDtoBalcaoDto)
                .orElseThrow(() -> new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_COM_ID + id));

    }

    public BalcaoDTO updateBalcao(Long id, BalcaoDTO updatedBalcaoDTO) {
        if (!balcaoRepository.existsById(id)) {
            throw new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_COM_ID + id);
        }

        Balcao updatedBalcao = BalcaoMapper.toEntityBalcao(updatedBalcaoDTO);
        updatedBalcao.setId(id);
        Balcao savedBalcao = balcaoRepository.save(updatedBalcao);
        return BalcaoMapper.toDtoBalcaoDto(savedBalcao);
    }

    public void deleteBalcao(Long id) {
        if (!balcaoRepository.existsById(id)) {
            throw new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_COM_ID + id);
        }
        balcaoRepository.deleteById(id);
    }
}
