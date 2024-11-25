package com.movilidad.SistemaMultas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="imagenes")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private String descripcion;
}
