package com.helpdesk.api.repository;

import com.helpdesk.api.model.AtendenteBalcao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AtendenteBalcaoRepository extends JpaRepository<AtendenteBalcao, Long> {
    Optional<AtendenteBalcao> findByNome(String nomeAtendente);
}
