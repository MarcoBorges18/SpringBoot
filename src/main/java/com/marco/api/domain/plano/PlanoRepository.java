package com.marco.api.domain.plano;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanoRepository extends JpaRepository<PlanoDeSaude, Integer> {
    Page<PlanoDeSaude> findAllByDisponivelTrue(Pageable pageable);
}
