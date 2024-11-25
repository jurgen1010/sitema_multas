package com.movilidad.SistemaMultas.controller;

import com.movilidad.SistemaMultas.entity.Vehiculo;
import com.movilidad.SistemaMultas.service.TipoVehiculoService;
import com.movilidad.SistemaMultas.service.VehiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {
    private final VehiculoService vehiculoService;
    private final TipoVehiculoService tipoVehiculoService;

    public VehiculoController(VehiculoService vehiculoService, TipoVehiculoService tipoVehiculoService) {
        this.vehiculoService = vehiculoService;
        this.tipoVehiculoService = tipoVehiculoService;
    }

    @PostMapping
    public ResponseEntity<Vehiculo> registrarVehiculo(@RequestBody Vehiculo vehiculo) {
        return ResponseEntity.ok(vehiculoService.registrarVehiculo(vehiculo));
    }

    @GetMapping
    public ResponseEntity<List<Vehiculo>> obtenerVehiculos() {
        return ResponseEntity.ok(vehiculoService.obtenerVehiculos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> obtenerVehiculoPorId(@PathVariable Long id) {
        return vehiculoService.obtenerVehiculoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> actualizarVehiculo(@PathVariable Long id, @RequestBody Vehiculo vehiculo,
                                                       @RequestParam Long idTipoVehiculo) {
        if (!vehiculoService.obtenerPorId(idTipoVehiculo).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehiculo no encontrada con ID: " + id);
        }

        if (!tipoVehiculoService.obtenerPorId(idTipoVehiculo).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TipoVehiculo no encontrada con ID: " + idTipoVehiculo);
        }

        return ResponseEntity.ok(vehiculoService.actualizarVehiculo(id,vehiculo,idTipoVehiculo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVehiculo(@PathVariable Long id) {
        if (!vehiculoService.obtenerPorId(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehiculo no encontrada con ID: " + id);
        }
        vehiculoService.eliminarVehiculoPorId(id);
        return ResponseEntity.noContent().build();
    }
}
