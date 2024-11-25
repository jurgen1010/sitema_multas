package com.movilidad.SistemaMultas.service;

import com.movilidad.SistemaMultas.entity.Imagen;
import com.movilidad.SistemaMultas.repository.ImagenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagenService {
    private final ImagenRepository imagenRepository;

    public ImagenService(ImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }

    public List<Imagen> obtenerTodasImagenes() {
        return imagenRepository.findAll();
    }

    public Imagen registrarImagen(Imagen imagen) {
        return imagenRepository.save(imagen);
    }

    public Optional<Imagen> obtenerImagenPorId(Long id) {
        return imagenRepository.findById(id);
    }
}
