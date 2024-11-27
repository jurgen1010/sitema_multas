package com.movilidad.SistemaMultas.controller;

import com.movilidad.SistemaMultas.entity.Imagen;
import com.movilidad.SistemaMultas.service.ImagenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imagenes")
@CrossOrigin(origins = "http://localhost:3000")

public class ImagenController {
    private final ImagenService imagenService;

    public ImagenController(ImagenService imagenService) {
        this.imagenService = imagenService;
    }

    @GetMapping
    public ResponseEntity<List<Imagen>> obtenerTodas() {
        return ResponseEntity.ok(imagenService.obtenerTodasImagenes());
    }

    @PostMapping
    public ResponseEntity<Imagen> registrarImagen(@RequestBody Imagen imagen) {
        return ResponseEntity.ok(imagenService.registrarImagen(imagen));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imagen> obtenerPorId(@PathVariable Long id) {
        return imagenService.obtenerImagenPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }
}
