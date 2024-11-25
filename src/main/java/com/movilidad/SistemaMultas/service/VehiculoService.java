package com.movilidad.SistemaMultas.service;

import com.movilidad.SistemaMultas.entity.TipoVehiculo;
import com.movilidad.SistemaMultas.entity.Vehiculo;
import com.movilidad.SistemaMultas.repository.TipoVehiculoRepository;
import com.movilidad.SistemaMultas.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {
    private final VehiculoRepository vehiculoRepository;
    private final TipoVehiculoRepository tipoVehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository, TipoVehiculoRepository tipoVehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
        this.tipoVehiculoRepository = tipoVehiculoRepository;
    }

    public Vehiculo registrarVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public List<Vehiculo> obtenerVehiculos() {
        return vehiculoRepository.findAll();
    }

    public Optional<Vehiculo> obtenerVehiculoPorId(Long id) {
        return vehiculoRepository.findById(id);
    }

    public Vehiculo actualizarVehiculo(Long id, Vehiculo vehiculo, Long idTipoVehiculo) {
        if (!vehiculoRepository.existsById(id)) {
            throw new IllegalArgumentException("Vehiculo no encontrado con ID: " + id);
        }

        if (idTipoVehiculo != null) {
            TipoVehiculo tipoVehiculo = tipoVehiculoRepository.findById(idTipoVehiculo)
                    .orElseThrow(() -> new IllegalArgumentException("TipoVehiculo no encontrada con ID: " + idTipoVehiculo));
            vehiculo.setTipoVehiculo(tipoVehiculo);
        }
        vehiculo.setId(id);
        return vehiculoRepository.save(vehiculo);
    }

    public Optional<Vehiculo> obtenerPorId(Long id) {
        return vehiculoRepository.findById(id);
    }

    public void eliminarVehiculoPorId(Long id) {
        Optional<Vehiculo> optionalVehiculo = vehiculoRepository.findById(id);
        if (optionalVehiculo.isPresent()){
            Vehiculo vehiculo = optionalVehiculo.get();
            vehiculo.setTipoVehiculo(null); //desvinculamos el tipoVehiculo
            vehiculoRepository.save(vehiculo); //guardamos los cambios
            vehiculoRepository.delete(vehiculo);//luego eliminamos el vehiculo
        }else{
            throw new IllegalArgumentException("Vehiculo no encontrado con ID: " + id);
        }
    }
}
