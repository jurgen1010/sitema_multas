package com.movilidad.SistemaMultas.service;

import com.movilidad.SistemaMultas.entity.Infraccion;
import com.movilidad.SistemaMultas.exception.ResourceNotFoundException;
import com.movilidad.SistemaMultas.repository.InfraccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportesService {
    private final InfraccionRepository infraccionRepository;

    public ReportesService(InfraccionRepository infraccionRepository) {
        this.infraccionRepository = infraccionRepository;
    }

    /**
     * Reporte 1: Cantidad de infracciones por tipo de vehículo.
     */
    public Map<String, Long> obtenerInfraccionesPorTipoVehiculo() {
        List<Infraccion> infracciones = infraccionRepository.findAll();
        if (infracciones.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron infracciones registradas.");
        }
        return infracciones.stream()
                .collect(Collectors.groupingBy(
                        infraccion -> infraccion.getTipoVehiculoId().toString(), //TODO: Cambiar por el nombre del tipo de vehículo
                        Collectors.counting()
                ));
    }

    /**
     * Reporte 2: Infracciones por ubicación (comuna/sector).
     */
    public Map<String, Long> obtenerInfraccionesPorUbicacion() {
        List<Infraccion> infracciones = infraccionRepository.findAll();
        if (infracciones.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron infracciones registradas.");
        }
        return infracciones.stream()
                .collect(Collectors.groupingBy(
                        Infraccion::getUbicacion,
                        Collectors.counting()
                ));
    }

    /**
     * Reporte 3: Cantidad de infracciones por fecha.
     */
    public Map<String, Long> obtenerInfraccionesPorFecha() {
        List<Infraccion> infracciones = infraccionRepository.findAll();
        if (infracciones.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron infracciones registradas.");
        }
        return infracciones.stream()
                .collect(Collectors.groupingBy(
                        infraccion -> infraccion.getFecha().toString(), // Agregación diaria
                        Collectors.counting()
                ));
    }

    /**
     * Reporte 4: Cantidad de infracciones por hora.
     */
    public Map<String, Long> obtenerInfraccionesPorHora() {
        List<Infraccion> infracciones = infraccionRepository.findAll();
        if (infracciones.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron infracciones registradas.");
        }
        return infracciones.stream()
                .collect(Collectors.groupingBy(
                        infraccion -> infraccion.getFecha().getHour() + ":00", // Agrupación por hora
                        Collectors.counting()
                ));
    }
}
