package com.movilidad.SistemaMultas.service;

import com.movilidad.SistemaMultas.entity.Imagen;
import com.movilidad.SistemaMultas.entity.Infraccion;
import com.movilidad.SistemaMultas.entity.Vehiculo;
import com.movilidad.SistemaMultas.repository.ImagenRepository;
import com.movilidad.SistemaMultas.repository.InfraccionRepository;
import com.movilidad.SistemaMultas.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InfraccionService {
    private final InfraccionRepository infraccionRepository;
    private final ImagenRepository imagenRepository;
    private final VehiculoRepository vehiculoRepository;

    public InfraccionService(InfraccionRepository infraccionRepository,
                             ImagenRepository imagenRepository,
                             VehiculoRepository vehiculoRepository) {
        this.infraccionRepository = infraccionRepository;
        this.imagenRepository = imagenRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    public List<Infraccion> obtenerTodas() {
        return infraccionRepository.findAll();
    }

    public List<Infraccion> obtenerTodasInfracciones() {
        return infraccionRepository.findAll();
    }


    public Optional<Infraccion> obtenerPorId(Long id) {
        return infraccionRepository.findById(id);
    }

    public Infraccion crear(Infraccion infraccion) {
        return infraccionRepository.save(infraccion);
    }

    public Infraccion registrarInfraccion(Infraccion infraccion, Long idImagen, Long idVehiculo) {
        Imagen imagen = imagenRepository.findById(idImagen)
                .orElseThrow(() -> new IllegalArgumentException("Imagen no encontrada"));

        Vehiculo vehiculo = vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        infraccion.setImagen(imagen);
        infraccion.setVehiculo(vehiculo);
        return infraccionRepository.save(infraccion);
    }

    public Infraccion actualizar(Long id, Infraccion infraccion, Long idImagen, Long idVehiculo) {
        if (!infraccionRepository.existsById(id)) {
            throw new IllegalArgumentException("Infracción no encontrada con ID: " + id);
        }
        if (idImagen != null) {
            Imagen imagen = imagenRepository.findById(idImagen)
                    .orElseThrow(() -> new IllegalArgumentException("Imagen no encontrada con ID: " + idImagen));
            infraccion.setImagen(imagen);
        }
        if (idVehiculo != null) {
            Vehiculo vehiculo = vehiculoRepository.findById(idVehiculo)
                    .orElseThrow(() -> new IllegalArgumentException("Imagen no encontrada con ID: " + idImagen));
            infraccion.setVehiculo(vehiculo);
        }
        infraccion.setId(id);
        return infraccionRepository.save(infraccion);
    }

    public void eliminar(Long id) {
        Optional<Infraccion> infraccionOpt = infraccionRepository.findById(id);
        if (infraccionOpt.isPresent()) {
            Infraccion infraccion = infraccionOpt.get();
            infraccion.setImagen(null); // Desvincular imagen
            infraccion.setVehiculo(null); // Desvincular vehículo
            infraccionRepository.save(infraccion); // Guardar los cambios
            infraccionRepository.delete(infraccion); // Luego eliminar
        } else {
            throw new IllegalArgumentException("Infracción no encontrada" + id);
        }

    }

    public boolean existePorId(Long id) {
        return infraccionRepository.existsById(id);
    }

}
