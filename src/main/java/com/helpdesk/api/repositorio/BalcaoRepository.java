package com.helpdesk.api.repositorio;

import com.helpdesk.api.model.Balcao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalcaoRepository extends JpaRepository<Balcao, Long> {
}
