package com.helpdesk.api.service;

import com.helpdesk.api.exception.BalcaoException;
import com.helpdesk.api.mapper.BalcaoMapper;
import com.helpdesk.api.model.AtendenteBalcao;
import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.model.dto.BalcaoDTO;
import com.helpdesk.api.repository.AtendenteBalcaoRepository;
import com.helpdesk.api.repository.BalcaoRepository;
import com.helpdesk.api.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BalcaoServiceImpl {

    private final BalcaoRepository balcaoRepository;
    private final AtendenteBalcaoRepository atendenteBalcaoRepository;
    private final BalcaoMapper balcaoMapper;

    public BalcaoDTO create(BalcaoDTO balcaoDTO) {
        AtendenteBalcao atendente = balcaoDTO.getAtendente().getId() != null
                ? atendenteBalcaoRepository.findById(balcaoDTO.getAtendente().getId())
                .orElseThrow(() -> new BalcaoException(MessageConstants.ATENDENTE_COM_O_ID_NAO_EXISTE))
                : atendenteBalcaoRepository.save(new AtendenteBalcao(null, balcaoDTO.getAtendente().getNome(), new ArrayList<>()));

        Balcao balcao = balcaoMapper.balcaoDTOToBalcao(balcaoDTO);
        balcao.setAtendente(atendente);
        Balcao savedBalcao = balcaoRepository.save(balcao);
        return balcaoMapper.balcaoToBalcaoDTO(savedBalcao);
    }

    public List<BalcaoDTO> getAllBalcoes() {
        return balcaoRepository.findAll().stream()
                .map(balcaoMapper::balcaoToBalcaoDTO)
                .collect(Collectors.toList());
    }

    public BalcaoDTO getBalcaoById(Long id) {
        return balcaoRepository.findById(id)
                .map(balcaoMapper::balcaoToBalcaoDTO)
                .orElseThrow(() -> new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_COM_ID + id));
    }

    public BalcaoDTO updateBalcao(Long id, BalcaoDTO updatedBalcaoDTO) {
        Balcao existingBalcao = balcaoRepository.findById(id)
                .orElseThrow(() -> new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_COM_ID + id));

        Balcao updatedBalcao = balcaoMapper.balcaoDTOToBalcao(updatedBalcaoDTO);
        updatedBalcao.setId(id);
        updatedBalcao.setAtendente(existingBalcao.getAtendente());

        Balcao savedBalcao = balcaoRepository.save(updatedBalcao);
        return balcaoMapper.balcaoToBalcaoDTO(savedBalcao);
    }

    public void deleteBalcao(Long id) {
        if (!balcaoRepository.existsById(id)) {
            throw new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_COM_ID + id);
        }
        balcaoRepository.deleteById(id);
    }
}
