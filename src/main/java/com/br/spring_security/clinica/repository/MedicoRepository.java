package com.br.spring_security.clinica.repository;

import com.br.spring_security.clinica.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
}