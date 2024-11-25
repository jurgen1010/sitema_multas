package com.movilidad.SistemaMultas.controller;

import com.movilidad.SistemaMultas.entity.TipoVehiculo;
import com.movilidad.SistemaMultas.service.TipoVehiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/tipos-vehiculo")
public class TipoVehiculoController {
    private final TipoVehiculoService tipoVehiculoService;

    public TipoVehiculoController(TipoVehiculoService tipoVehiculoService) {
        this.tipoVehiculoService = tipoVehiculoService;
    }

    @GetMapping
    public List<TipoVehiculo> obtenerTodos() {
        return tipoVehiculoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoVehiculo> obtenerPorId(@PathVariable Long id) {

            return tipoVehiculoService.obtenerPorId(id).
                    map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public TipoVehiculo crear(@RequestBody TipoVehiculo tipoVehiculo) {
        return tipoVehiculoService.crear(tipoVehiculo);
    }

    @PutMapping("/{id}")
    public TipoVehiculo actualizar(@PathVariable Long id, @RequestBody TipoVehiculo tipoVehiculo) {
        try {
            return tipoVehiculoService.actualizar(id, tipoVehiculo);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        if (!tipoVehiculoService.existePorId(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de vehículo no encontrado con ID: " + id);
        }
        tipoVehiculoService.eliminar(id);
    }
}
