package com.movilidad.SistemaMultas.repository;

import com.movilidad.SistemaMultas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByCorreo(String correo);
}
