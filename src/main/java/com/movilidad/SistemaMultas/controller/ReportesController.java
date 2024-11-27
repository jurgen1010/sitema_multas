package com.movilidad.SistemaMultas.controller;

import com.movilidad.SistemaMultas.exception.ResourceNotFoundException;
import com.movilidad.SistemaMultas.service.ReportesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/reportes")
@CrossOrigin(origins = "http://localhost:3000")

public class ReportesController {
    private final ReportesService reportesService;

    public ReportesController(ReportesService reportesService) {
        this.reportesService = reportesService;
    }

    /**
     * Reporte 1: Cantidad de infracciones por tipo de vehículo.
     */
    @GetMapping("/infracciones-por-tipo-vehiculo")
    public ResponseEntity<Map<String, Long>> infraccionesPorTipoVehiculo() {
        Map<String, Long> reporte = reportesService.obtenerInfraccionesPorTipoVehiculo();
        return ResponseEntity.ok(reporte);
    }

    /**
     * Reporte 2: Infracciones por ubicación (comuna/sector).
     */
    @GetMapping("/infracciones-por-ubicacion")
    public ResponseEntity<Map<String, Long>> infraccionesPorUbicacion() {
        Map<String, Long> reporte = reportesService.obtenerInfraccionesPorUbicacion();
        return ResponseEntity.ok(reporte);
    }

    /**
     * Reporte 3: Cantidad de infracciones por fecha.
     */
    @GetMapping("/infracciones-por-fecha")
    public ResponseEntity<Map<String, Long>> infraccionesPorFecha() {
        Map<String, Long> reporte = reportesService.obtenerInfraccionesPorFecha();
        return ResponseEntity.ok(reporte);
    }

    /**
     * Reporte 4: Cantidad de infracciones por hora.
     */
    @GetMapping("/infracciones-por-hora")
    public ResponseEntity<Map<String, Long>> infraccionesPorHora() {
        Map<String, Long> reporte = reportesService.obtenerInfraccionesPorHora();
        return ResponseEntity.ok(reporte);
    }

    /**
     * Manejo global de excepciones 404 para recursos no encontrados.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
