package com.helpdesk.api.service;

import com.helpdesk.api.exception.BalcaoException;
import com.helpdesk.api.model.Balcao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BalcaoService {
    Balcao createBalcaoAtendimento(Balcao balcao);

    List<Balcao> getAllBalcoes();

    Optional<Balcao> getBalcaoById(Long id);

    Optional<Balcao> updateBalcao(Long id, Balcao updatedBalcao);

    void deleteBalcao(Long id);
}
