package com.movilidad.SistemaMultas.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="infracciones")
public class Infraccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    private String ubicacion;

    // EN caso de que quiera se eliminen automáticamente las entidades relacionadas < cascade = CascadeType.ALL>
    @ManyToOne()
    @JoinColumn(name = "vehiculo_id", nullable = true) // Relación con Vehiculo
    private Vehiculo vehiculo;

    @Column(name = "tipo_vehiculo_id", nullable = true)
    private Long tipoVehiculoId;

    @ManyToOne
    @JoinColumn(name = "id_imagen") // Relación con  Imagen
    private Imagen imagen;

    private int numeroPasajeros;

    private String descripcion;

    @PrePersist
    public void prePersist(){
        if (fecha == null) {
            fecha = LocalDateTime.now();
        }
    }
}
