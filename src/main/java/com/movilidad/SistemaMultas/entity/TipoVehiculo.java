package com.movilidad.SistemaMultas.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="tipos_vehiculo")
public class TipoVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nombre;

    private String descripcion;
}
