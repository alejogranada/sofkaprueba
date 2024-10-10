package com.example.cuenta_movimientos_service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cliente")
@Data
public class Cliente extends Persona {

    @Column(unique = true, nullable = false)
    private String clienteId;

    private String contraseña;

    private boolean estado;

}