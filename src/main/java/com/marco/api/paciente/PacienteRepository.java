package com.marco.api.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository <Paciente, Integer> {
    Page<Paciente> findAllByAtivoTrue(Pageable pageable);
}
