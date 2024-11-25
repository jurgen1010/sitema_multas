package com.movilidad.SistemaMultas.repository;

import com.movilidad.SistemaMultas.entity.Infraccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfraccionRepository extends JpaRepository<Infraccion, Long> {
}
