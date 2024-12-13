package com.helpdesk.api.repository;

import com.helpdesk.api.model.AtendenteBalcao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendenteBalcaoRepository extends JpaRepository<AtendenteBalcao, Long> {
}
