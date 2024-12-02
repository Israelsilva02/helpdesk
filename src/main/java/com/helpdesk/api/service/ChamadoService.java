package com.helpdesk.api.service;

import com.helpdesk.api.exception.BalcaoException;
import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.EstadoChamado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ChamadoService {
    Chamado createChamado(Chamado chamado) throws Exception;
    Page<Chamado> getChamadosByCustomerId(Long customerId, Pageable pageable);
    List<Chamado> getChamadosPorEstado(EstadoChamado estado);
    Optional<Chamado> getChamadoById(Long id);
    Chamado updateEstadoChamado(Long id, EstadoChamado novoEstado) throws BalcaoException;
}