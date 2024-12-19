package com.helpdesk.api.service;

import com.helpdesk.api.mapper.AtendenteBalcaoMapper;
import com.helpdesk.api.model.AtendenteBalcao;
import com.helpdesk.api.model.dto.AtendenteBalcaoDTO;
import com.helpdesk.api.repository.AtendenteBalcaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AtendenteBalcaoServiceImpl {

    private final AtendenteBalcaoRepository atendenteBalcaoRepository;
    private final AtendenteBalcaoMapper atendenteBalcaoMapper;

    public List<AtendenteBalcaoDTO> findAll() {
        List<AtendenteBalcao> atendentes = atendenteBalcaoRepository.findAll();
        return atendentes.stream()
                .map(atendenteBalcaoMapper::atendenteBalcaoToAtendenteBalcaoDTO)
                .collect(Collectors.toList());
    }

    public Optional<AtendenteBalcaoDTO> findById(Long id) {
        return atendenteBalcaoRepository.findById(id)
                .map(atendenteBalcaoMapper::atendenteBalcaoToAtendenteBalcaoDTO);
    }

    public AtendenteBalcaoDTO save(AtendenteBalcaoDTO atendenteDTO) {
        AtendenteBalcao atendente = atendenteBalcaoMapper.atendenteBalcaoDTOToAtendenteBalcao(atendenteDTO);
        AtendenteBalcao savedAtendente = atendenteBalcaoRepository.save(atendente);
        return atendenteBalcaoMapper.atendenteBalcaoToAtendenteBalcaoDTO(savedAtendente);
    }

    public void deleteById(Long id) {
        atendenteBalcaoRepository.deleteById(id);
    }
}
