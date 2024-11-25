package com.movilidad.SistemaMultas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="vehiculos")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;
    private String color;
    private String modelo;

    @ManyToOne
    @JoinColumn(name = "tipo_vehiculo_id", nullable = true)
    private TipoVehiculo tipoVehiculo;
}
