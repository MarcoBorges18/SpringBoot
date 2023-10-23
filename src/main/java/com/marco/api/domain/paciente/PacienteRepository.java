package com.marco.api.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository <Paciente, Integer> {
    Page<Paciente> findAllByAtivoTrue(Pageable pageable);
}
