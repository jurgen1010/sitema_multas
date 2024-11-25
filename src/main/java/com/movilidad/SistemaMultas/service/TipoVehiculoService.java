package com.movilidad.SistemaMultas.service;


import com.movilidad.SistemaMultas.entity.TipoVehiculo;
import com.movilidad.SistemaMultas.repository.TipoVehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoVehiculoService {
    private final TipoVehiculoRepository tipoVehiculoRepository;

    public TipoVehiculoService(TipoVehiculoRepository tipoVehiculoRepository) {
        this.tipoVehiculoRepository = tipoVehiculoRepository;
    }

    public List<TipoVehiculo> obtenerTodos() {
        return tipoVehiculoRepository.findAll();
    }

    public Optional<TipoVehiculo> obtenerPorId(Long id) {
        return tipoVehiculoRepository.findById(id);
    }

    public TipoVehiculo crear(TipoVehiculo tipoVehiculo) {
        return tipoVehiculoRepository.save(tipoVehiculo);
    }

    public TipoVehiculo actualizar(Long id, TipoVehiculo tipoVehiculo) {
        Optional<TipoVehiculo> existente = tipoVehiculoRepository.findById(id);
        if (existente.isPresent()) {
            tipoVehiculo.setId(id);
            return tipoVehiculoRepository.save(tipoVehiculo);
        } else {
            throw new IllegalArgumentException("Tipo de vehículo no encontrado con ID: " + id);
        }
    }

    public void eliminar(Long id) {
        tipoVehiculoRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
    return tipoVehiculoRepository.existsById(id);
}
}

