package com.helpdesk.api.service.impl;

import com.helpdesk.api.exception.BalcaoException;
import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.repository.BalcaoRepository;
import com.helpdesk.api.service.BalcaoService;
import com.helpdesk.api.util.MessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalcaoServiceImpl implements BalcaoService {

    private final BalcaoRepository balcaoRepository;

    @Autowired
    public BalcaoServiceImpl(BalcaoRepository balcaoRepository) {
        this.balcaoRepository = balcaoRepository;
    }

    @Override
    public Balcao createBalcaoAtendimento(Balcao balcao) {
        if (balcao.getId() == null || balcaoRepository.findById(balcao.getId()).isEmpty()) {
            return balcaoRepository.save(balcao);

        }
        throw new BalcaoException(MessageConstants.BALCAO_COM_ID_JA_EXISTE + balcao.getId());
    }

    @Override
    public List<Balcao> getAllBalcoes() {
        return balcaoRepository.findAll();

    }

    @Override
    public Optional<Balcao> getBalcaoById(Long id) {
        Optional<Balcao> optionalBalcao = balcaoRepository.findById(id);
        if (optionalBalcao.isPresent()) {
            return optionalBalcao;
        } else {
            throw new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_C0M_ID + id);
        }

    }

    @Override
    public Optional<Balcao> updateBalcao(Long id, Balcao updatedBalcao) {

        Optional<Balcao> optionalBalcao = balcaoRepository.findById(id);
        if (optionalBalcao.isPresent()) {

            Balcao balcaoExistente = optionalBalcao.get();
            balcaoExistente.setAtendente(updatedBalcao.getAtendente());
            return Optional.of(balcaoRepository.save(balcaoExistente));
        }
        return optionalBalcao;

    }

    @Override
    public void deleteBalcao(Long id) {
        Optional<Balcao> optionalBalcao = balcaoRepository.findById(id);
        if (optionalBalcao.isEmpty()) {
            throw new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_C0M_ID + id);
        }
        balcaoRepository.deleteById(id);

    }
}
