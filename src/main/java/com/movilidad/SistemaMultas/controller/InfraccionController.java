package com.movilidad.SistemaMultas.controller;

import com.movilidad.SistemaMultas.entity.Infraccion;
import com.movilidad.SistemaMultas.entity.Vehiculo;
import com.movilidad.SistemaMultas.exception.ResourceNotFoundException;
import com.movilidad.SistemaMultas.service.ImagenService;
import com.movilidad.SistemaMultas.service.InfraccionService;
import com.movilidad.SistemaMultas.service.VehiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/infracciones")
public class InfraccionController {

    private final InfraccionService infraccionService;
    private final ImagenService imagenService;
    private final VehiculoService vehiculoService;


    public InfraccionController(InfraccionService infraccionService, ImagenService imagenService,
                                VehiculoService vehiculoService) {
        this.infraccionService = infraccionService;
        this.imagenService = imagenService;
        this.vehiculoService = vehiculoService;
    }

    @GetMapping
    public ResponseEntity<List<Infraccion>> obtenerTodasInfracciones() {
        return ResponseEntity.ok(infraccionService.obtenerTodasInfracciones());
    }

    @GetMapping("/{id}")
    public Infraccion obtenerPorId(@PathVariable Long id) {
        return infraccionService.obtenerPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Infracción no encontrada con ID: " + id));
    }

    @PostMapping
    public ResponseEntity<Infraccion> registrarInfraccion(@RequestBody Infraccion infraccion
                                                            ,@RequestParam Long idImagen
                                                             ,@RequestParam Long idVehiculo) {

        if (!imagenService.obtenerImagenPorId(idImagen).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Vehiculo vehiculo = vehiculoService.obtenerVehiculoPorId(idVehiculo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehiculo no encontrado con ID: " + idVehiculo));

        infraccion.setVehiculo(vehiculo);
        infraccion.setTipoVehiculoId(vehiculo.getTipoVehiculo().getId());

        return ResponseEntity.ok(infraccionService.registrarInfraccion(infraccion, idImagen,idVehiculo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Infraccion> actualizar(@PathVariable Long id, @RequestBody Infraccion infraccion,
                                 @RequestParam(required = false) Long idImagen
                                , @RequestParam Long idVehiculo) {
        if (!infraccionService.existePorId(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Infracción no encontrada con ID: " + id);
        }
        if (!imagenService.obtenerImagenPorId(idImagen).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Imagen no encontrada con ID: " + idImagen);
        }

        Vehiculo vehiculo = vehiculoService.obtenerVehiculoPorId(idVehiculo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehiculo no encontrado con ID: " + idVehiculo));

        infraccion.setVehiculo(vehiculo);
        infraccion.setTipoVehiculoId(vehiculo.getTipoVehiculo().getId());

        if (infraccion.getFecha() == null) {
            infraccion.setFecha(LocalDateTime.now());
        }
        return ResponseEntity.ok(infraccionService.actualizar(id, infraccion, idImagen, idVehiculo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!infraccionService.existePorId(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Infracción no encontrada con ID: " + id);
        }
        infraccionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
