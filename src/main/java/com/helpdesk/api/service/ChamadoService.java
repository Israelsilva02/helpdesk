package com.helpdesk.api.service;

import com.helpdesk.api.exception.BalcaoException;
import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.EstadoChamado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ChamadoService {
    Chamado createChamado(Chamado chamado) ;
    List<Chamado> getAllChamados() ;
    List<Chamado> getChamadosPorEstado(EstadoChamado estado);
    Optional<Chamado> getChamadoById(Long id);
    Optional<Chamado> updateChamado(Long id, Chamado chamadoAtualizado);
    void deleteChamado(Long id);
}
