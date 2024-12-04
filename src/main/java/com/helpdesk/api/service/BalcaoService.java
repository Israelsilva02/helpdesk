package com.helpdesk.api.service;

import com.helpdesk.api.exception.BalcaoException;
import com.helpdesk.api.model.Balcao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface BalcaoService {
    Balcao createBalcaoAtendimento(Balcao balcao);
    List<Balcao> getAllBalcoes() throws BalcaoException;
    Optional<Balcao> getBalcaoById(Long id) throws BalcaoException;
    Balcao updateBalcao(Long id, Balcao updatedBalcao) throws BalcaoException;
    void deleteBalcao(Long id) throws BalcaoException;
}