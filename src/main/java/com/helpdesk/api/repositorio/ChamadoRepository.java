package com.helpdesk.api.repositorio;

import com.helpdesk.api.model.Balcao;
import com.helpdesk.api.model.Chamado;
import com.helpdesk.api.model.EstadoChamado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    Page<Chamado> findAllByCustomerId(Long customerId, Pageable pageable);

    List<Chamado> findByEstadoChamado(EstadoChamado estadoChamado);

    List<Chamado> findByCustomerIdAndSerialNumberAndEstadoChamadoNot(Long customerId, String serialNumber, EstadoChamado estadoChamado);

    List<Chamado> findByBalcaoAndEstadoChamadoNot(Balcao balcao, EstadoChamado estadoChamado);
}