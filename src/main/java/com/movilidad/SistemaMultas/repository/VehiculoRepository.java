package com.movilidad.SistemaMultas.repository;

import com.movilidad.SistemaMultas.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
}
