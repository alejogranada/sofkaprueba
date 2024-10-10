package com.example.cuenta_movimientos_service.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "persona")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String genero;
    private int edad;

    @Column(unique = true, nullable = false)
    private String identificacion;

    private String direccion;
    private String telefono;

}