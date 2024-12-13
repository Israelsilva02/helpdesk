package com.helpdesk.api.repository;


import com.helpdesk.api.model.HorariosDisponiveis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HorariosDisponiveisRepository extends JpaRepository<HorariosDisponiveis,Long> {
    Optional<HorariosDisponiveis>findById(Long id);

}
