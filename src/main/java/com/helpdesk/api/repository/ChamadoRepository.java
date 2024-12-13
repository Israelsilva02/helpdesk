package com.helpdesk.api.repository;

import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.EstadoChamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
  Optional<Chamado> findById(Long id);
  List<Chamado> findByEstadoChamado(EstadoChamado estadoChamado);

}
