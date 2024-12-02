package com.helpdesk.api.service;

import com.helpdesk.api.exception.BalcaoException;
import com.helpdesk.api.model.Balcao;

import java.util.List;
import java.util.Optional;

public interface BalcaoService {
    Balcao createBalcao(Balcao balcao) throws BalcaoException;
    List<Balcao> getAllBalcoes() throws BalcaoException;
    Optional<Balcao> getBalcaoById(Long id) throws BalcaoException;
    Balcao updateBalcao(Long id, Balcao updatedBalcao) throws BalcaoException;
    void deleteBalcao(Long id) throws BalcaoException;
}