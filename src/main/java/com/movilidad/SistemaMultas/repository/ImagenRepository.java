package com.movilidad.SistemaMultas.repository;

import com.movilidad.SistemaMultas.entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagenRepository extends JpaRepository<Imagen, Long> {
}
