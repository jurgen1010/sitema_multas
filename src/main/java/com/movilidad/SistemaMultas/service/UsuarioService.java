package com.movilidad.SistemaMultas.service;

import com.movilidad.SistemaMultas.entity.Usuario;
import com.movilidad.SistemaMultas.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    // New actualizarUsuario method
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    // Update fields
                    usuario.setCorreo(usuarioActualizado.getCorreo());
                    usuario.setNombre(usuarioActualizado.getNombre());
                    usuario.setContraseña(usuarioActualizado.getContraseña());
                    usuario.setRol(usuarioActualizado.getRol());
                    // Save updated entity
                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("Usuario con ID " + id + " no encontrado"));
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

}
