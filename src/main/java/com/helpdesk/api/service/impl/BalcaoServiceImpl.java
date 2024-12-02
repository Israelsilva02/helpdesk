package com.helpdesk.api.service.impl;

import com.helpdesk.api.exception.BalcaoException;
import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.repositorio.BalcaoRepository;
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
    public Balcao createBalcao(Balcao balcao) throws BalcaoException {
        try {
            return balcaoRepository.save(balcao);
        } catch (Exception e) {
            throw new BalcaoException(MessageConstants.OCORREU_UM_ERRO_AO_CRIAR_BALCAO, e);
        }
    }

    @Override
    public List<Balcao> getAllBalcoes() throws BalcaoException {
        try {
            return balcaoRepository.findAll();
        } catch (Exception e) {
            throw new BalcaoException(MessageConstants.OCORREU_UM_ERRO_AO_OBTER_TODOS_OS_BALCOES, e);
        }
    }

    @Override
    public Optional<Balcao> getBalcaoById(Long id) throws BalcaoException {
        try {
            Optional<Balcao> optionalBalcao = balcaoRepository.findById(id);
            if (optionalBalcao.isPresent()) {
                return optionalBalcao;
            } else {
                throw new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_C0M_ID + id);
            }
        } catch (Exception e) {
            throw new BalcaoException(MessageConstants.OCORREU_UM_ERRO_AO_OBTER_O_BALCAO_COM_ID + id, e);
        }
    }

    @Override
    public Balcao updateBalcao(Long id, Balcao updatedBalcao) throws BalcaoException {
        try {
            Optional<Balcao> optionalBalcao = balcaoRepository.findById(id);
            if (optionalBalcao.isPresent()) {
                Balcao balcaoExistente = optionalBalcao.get();
                balcaoExistente.setNomeAtendente(updatedBalcao.getNomeAtendente());
                return balcaoRepository.save(balcaoExistente);
            } else {
                throw new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_C0M_ID + id);
            }
        } catch (Exception e) {
            throw new BalcaoException(MessageConstants.OCORREU_UM_ERRO_AO_ATUALIZAR_O_BALCAO_COM_ID + id, e);
        }
    }

    @Override
    public void deleteBalcao(Long id) throws BalcaoException {
        try {
            if (balcaoRepository.existsById(id)) {
                balcaoRepository.deleteById(id);
            } else {
                throw new BalcaoException(MessageConstants.BALCAO_NAO_ENCONTRADO_C0M_ID + id);
            }
        } catch (Exception e) {
            throw new BalcaoException(MessageConstants.OCORREU_UM_ERRO_AO_DELETAR_O_BALCAO_COM_ID + id, e);
        }
    }
}