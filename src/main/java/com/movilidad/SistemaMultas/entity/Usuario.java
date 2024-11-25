package com.movilidad.SistemaMultas.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String contraseña;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public enum Rol {
        ADMINISTRADOR, AUTORIZADO
    }
}
